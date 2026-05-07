package com.rooftop.fooddeliveryservice.controller;

import com.rooftop.fooddeliveryservice.model.CustomerOrderRequest;
import com.rooftop.fooddeliveryservice.model.DeliveryRequest;
import com.rooftop.fooddeliveryservice.model.DeliveryResponse;
import com.rooftop.fooddeliveryservice.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DeliveryService deliveryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldAllocateDriversSuccessfully() throws Exception {
        DeliveryRequest request = new DeliveryRequest(2, List.of(new CustomerOrderRequest(1, 10)));
        List<DeliveryResponse> responses = List.of(new DeliveryResponse("C1", "D1"));

        when(deliveryService.allocateDrivers(request)).thenReturn(responses);

        mockMvc.perform(post("/api/v1/delivery/allocate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customer").value("C1"))
                .andExpect(jsonPath("$[0].allocatedDriver").value("D1"));
    }

    @Test
    void shouldReturnBadRequestForInvalidPayload() throws Exception {
        String invalidRequest = """
                {
                  "totalDrivers": 0,
                  "orders": []
                }
                """;

        mockMvc.perform(post("/api/v1/delivery/allocate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequest)
                )
                .andExpect(status().isBadRequest());
    }
}
