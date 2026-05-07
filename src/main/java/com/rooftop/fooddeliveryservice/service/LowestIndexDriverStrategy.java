package com.rooftop.fooddeliveryservice.service;

import com.rooftop.fooddeliveryservice.entity.Driver;
import com.rooftop.fooddeliveryservice.model.AllocationStrategyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class LowestIndexDriverStrategy implements DriverAllocationStrategy {

    @Override
    public AllocationStrategyType getStrategyType() {
        return AllocationStrategyType.LOWEST_INDEX;
    }

    @Override
    public Optional<Driver> allocateDriver(List<Driver> drivers, Integer orderTime) {
        return drivers.stream()
                .filter(driver -> driver.getAvailableAt() <= orderTime)
                .min(Comparator.comparing(Driver::getId));
    }
}
