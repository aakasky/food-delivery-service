package com.rooftop.fooddeliveryservice.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record DeliveryRequest(
        @Min(1)
        Integer totalDrivers,

        @Valid
        @NotEmpty
        List<CustomerOrderRequest> orders
) {
}
