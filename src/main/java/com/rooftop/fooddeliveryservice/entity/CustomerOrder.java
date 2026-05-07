package com.rooftop.fooddeliveryservice.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerOrder {
    private Integer customerId;
    private Integer orderTime;
    private Integer travelTime;
}
