package com.rooftop.fooddeliveryservice.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CustomerOrderRequest(
        @NotNull
        @Min(0)
        Integer orderTime,

        @NotNull
        @Min(1)
        Integer travelTime
) {
}
