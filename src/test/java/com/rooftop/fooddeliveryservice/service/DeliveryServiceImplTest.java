package com.rooftop.fooddeliveryservice.service;

import com.rooftop.fooddeliveryservice.entity.Driver;
import com.rooftop.fooddeliveryservice.model.CustomerOrderRequest;
import com.rooftop.fooddeliveryservice.model.DeliveryRequest;
import com.rooftop.fooddeliveryservice.model.DeliveryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {

    @Mock
    private DriverStrategyFactory strategyFactory;

    @Mock
    private DriverAllocationStrategy allocationStrategy;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @BeforeEach
    void setup() {
        when(strategyFactory.getStrategy()).thenReturn(allocationStrategy);
    }

    @Test
    void shouldAllocateDriverSuccessfully() {
        DeliveryRequest request = new DeliveryRequest(2, List.of(new CustomerOrderRequest(1, 10)));

        Driver driver = Driver.builder().id(1).availableAt(0).build();

        when(allocationStrategy.allocateDriver(anyList(), eq(1))).thenReturn(Optional.of(driver));

        List<DeliveryResponse> responses = deliveryService.allocateDrivers(request);

        assertEquals(1, responses.size());
        assertEquals("C1", responses.get(0).customer());
        assertEquals("D1", responses.get(0).allocatedDriver());

        verify(strategyFactory, times(1)).getStrategy();
    }

    @Test
    void shouldReturnNoFoodWhenNoDriverAvailable() {
        DeliveryRequest request = new DeliveryRequest(1, List.of(new CustomerOrderRequest(1, 10)));

        when(allocationStrategy.allocateDriver(anyList(), eq(1))).thenReturn(Optional.empty());

        List<DeliveryResponse> responses = deliveryService.allocateDrivers(request);

        assertEquals(1, responses.size());
        assertEquals("No Food :-(", responses.get(0).allocatedDriver());
    }

    @Test
    void shouldProcessMultipleOrders() {

        DeliveryRequest request = new DeliveryRequest(2, List.of(new CustomerOrderRequest(1, 10), new CustomerOrderRequest(15, 5)));

        Driver driver1 = Driver.builder().id(1).availableAt(0).build();

        when(allocationStrategy.allocateDriver(anyList(), anyInt())).thenReturn(Optional.of(driver1));

        List<DeliveryResponse> responses = deliveryService.allocateDrivers(request);

        assertEquals(2, responses.size());
        assertEquals("D1", responses.get(0).allocatedDriver());
        assertEquals("D1", responses.get(1).allocatedDriver());
    }
}
