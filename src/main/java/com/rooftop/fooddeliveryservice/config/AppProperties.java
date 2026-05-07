package com.rooftop.fooddeliveryservice.config;

import com.rooftop.fooddeliveryservice.model.AllocationStrategyType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.allocation")
public class AppProperties {
    private AllocationStrategyType strategy;
}
