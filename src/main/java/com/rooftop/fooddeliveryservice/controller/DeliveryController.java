package com.rooftop.fooddeliveryservice.controller;

import com.rooftop.fooddeliveryservice.model.DeliveryRequest;
import com.rooftop.fooddeliveryservice.model.DeliveryResponse;
import com.rooftop.fooddeliveryservice.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/allocate")
    public List<DeliveryResponse> allocateDrivers(@Valid @RequestBody DeliveryRequest request) {
        return deliveryService.allocateDrivers(request);
    }
}
