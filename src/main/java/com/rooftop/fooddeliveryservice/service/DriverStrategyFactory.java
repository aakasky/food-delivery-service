package com.rooftop.fooddeliveryservice.service;

import com.rooftop.fooddeliveryservice.config.AppProperties;
import com.rooftop.fooddeliveryservice.model.AllocationStrategyType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DriverStrategyFactory {

    private final AppProperties appProperties;
    private final List<DriverAllocationStrategy> strategies;

    public DriverAllocationStrategy getStrategy() {
        Map<AllocationStrategyType, DriverAllocationStrategy> strategyMap = strategies.stream()
                .collect(Collectors.toMap(DriverAllocationStrategy::getStrategyType, Function.identity()));

        return strategyMap.get(appProperties.getStrategy());
    }
}
