package com.rooftop.fooddeliveryservice.service;

import com.rooftop.fooddeliveryservice.entity.Driver;
import com.rooftop.fooddeliveryservice.model.AllocationStrategyType;

import java.util.List;
import java.util.Optional;

public interface DriverAllocationStrategy {

    AllocationStrategyType getStrategyType();

    Optional<Driver> allocateDriver(List<Driver> drivers, Integer orderTime);
}