package com.project.order.controller;

import com.project.order.dto.OrderRequest;
import com.project.order.dto.OrderResponse;
import com.project.order.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger log =
            LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody OrderRequest request) {

        log.info("Received create order request: {}", request);

        OrderResponse response = orderService.createOrder(request);

        log.info("Order created successfully: {}", response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable String orderId) {

        log.info("Fetching order with id: {}", orderId);

        OrderResponse response = orderService.getOrderById(orderId);

        return ResponseEntity.ok(response);
    }
}