package com.rooftop.fooddeliveryservice.service;

import com.rooftop.fooddeliveryservice.model.DeliveryRequest;
import com.rooftop.fooddeliveryservice.model.DeliveryResponse;

import java.util.List;

public interface DeliveryService {
    List<DeliveryResponse> allocateDrivers(DeliveryRequest request);
}
