package com.project.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.order.dto.OrderRequest;
import com.project.order.dto.OrderResponse;
import com.project.order.entity.Order;
import com.project.order.kafka.OrderEventProducer;
import com.project.order.repository.OrderRepository;

@Service
public class OrderService {

    private static final Logger log =
            LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    public OrderService(
            OrderRepository orderRepository,
            OrderEventProducer orderEventProducer
    ) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
    }


    public OrderResponse createOrder(OrderRequest request) {

        log.info("Creating order: {}", request);

        Order order = new Order();

        order.setOrderId(request.getOrderId());
        order.setUserId(request.getUserId());
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setStatus(request.getStatus());


        // Save order in database
        Order savedOrder = orderRepository.save(order);

        log.info(
                "Order saved successfully: {}",
                savedOrder.getOrderId()
        );


        // Publish order event to Kafka
        orderEventProducer.sendOrderEvent(savedOrder);

        log.info(
                "Order event published to Kafka: {}",
                savedOrder.getOrderId()
        );


        return mapToResponse(savedOrder);
    }


    public OrderResponse getOrderById(String orderId) {

        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Order not found: " + orderId
                        )
                );

        return mapToResponse(order);
    }


    private OrderResponse mapToResponse(Order order) {

        return new OrderResponse(
                order.getOrderId(),
                order.getQuantity(),
                order.getStatus(),
                "Order processed successfully"
        );
    }
}