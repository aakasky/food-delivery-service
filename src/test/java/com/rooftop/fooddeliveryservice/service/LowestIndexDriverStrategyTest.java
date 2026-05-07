package com.rooftop.fooddeliveryservice.service;

import com.rooftop.fooddeliveryservice.entity.Driver;
import com.rooftop.fooddeliveryservice.model.AllocationStrategyType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LowestIndexDriverStrategyTest {

    private final LowestIndexDriverStrategy strategy = new LowestIndexDriverStrategy();

    @Test
    void shouldAllocateLowestIndexDriver() {
        List<Driver> drivers = List.of(
                Driver.builder()
                        .id(2)
                        .availableAt(0)
                        .build(),

                Driver.builder()
                        .id(1)
                        .availableAt(0)
                        .build()
        );

        Optional<Driver> result = strategy.allocateDriver(drivers, 5);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenNoDriverAvailable() {
        List<Driver> drivers = List.of(
                Driver.builder()
                        .id(1)
                        .availableAt(20)
                        .build(),

                Driver.builder()
                        .id(2)
                        .availableAt(30)
                        .build()
        );

        Optional<Driver> result = strategy.allocateDriver(drivers, 5);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnCorrectStrategyType() {
        assertEquals(AllocationStrategyType.LOWEST_INDEX, strategy.getStrategyType());
    }
}
