package com.rooftop.fooddeliveryservice.service;

import com.rooftop.fooddeliveryservice.entity.Driver;
import com.rooftop.fooddeliveryservice.model.CustomerOrderRequest;
import com.rooftop.fooddeliveryservice.model.DeliveryRequest;
import com.rooftop.fooddeliveryservice.model.DeliveryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DriverStrategyFactory strategyFactory;

    @Override
    public List<DeliveryResponse> allocateDrivers(DeliveryRequest request) {
        final DriverAllocationStrategy allocationStrategy = strategyFactory.getStrategy();
        log.info("Starting driver allocation");

        List<Driver> drivers = initializeDrivers(request.totalDrivers());
        List<DeliveryResponse> responses = new ArrayList<>();

        int customerCounter = 1;

        for (CustomerOrderRequest order : request.orders()) {
            int currentCustomer = customerCounter;
            log.info("Processing customer C{} orderTime={} travelTime={}", currentCustomer, order.orderTime(), order.travelTime());

            var allocatedDriver = allocationStrategy.allocateDriver(drivers, order.orderTime());

            if (allocatedDriver.isPresent()) {
                Driver driver = allocatedDriver.get();
                driver.setAvailableAt(order.orderTime() + order.travelTime());

                String driverName = "D" + driver.getId();
                responses.add(new DeliveryResponse("C" + currentCustomer, driverName));

                log.info("Allocated driver {} to customer C{}", driverName, currentCustomer);
            } else {
                responses.add(new DeliveryResponse("C" + currentCustomer, "No Food :-("));
                log.warn("No driver available for customer C{}", currentCustomer);
            }
            customerCounter++;
        }
        return responses;
    }

    private List<Driver> initializeDrivers(int totalDrivers) {
        List<Driver> drivers = new ArrayList<>();

        for (int i = 1; i <= totalDrivers; i++) {
            drivers.add(Driver.builder().id(i).availableAt(0).build());
        }

        return drivers;
    }
}
