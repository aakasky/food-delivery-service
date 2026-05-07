package com.rooftop.fooddeliveryservice.service;

import com.rooftop.fooddeliveryservice.config.AppProperties;
import com.rooftop.fooddeliveryservice.model.AllocationStrategyType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverStrategyFactoryTest {

    @Mock
    private AppProperties appProperties;

    @Mock
    private DriverAllocationStrategy strategy;

    @InjectMocks
    private DriverStrategyFactory factory;

    @Test
    void shouldReturnCorrectStrategy() {
        when(strategy.getStrategyType()).thenReturn(AllocationStrategyType.LOWEST_INDEX);
        when(appProperties.getStrategy()).thenReturn(AllocationStrategyType.LOWEST_INDEX);

        factory = new DriverStrategyFactory(appProperties, List.of(strategy));

        DriverAllocationStrategy result = factory.getStrategy();

        assertNotNull(result);
        assertEquals(strategy, result);
    }
}
