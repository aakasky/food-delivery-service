package com.rooftop.fooddeliveryservice.model;

public record DeliveryResponse(
        String customer,
        String allocatedDriver
) {
}
