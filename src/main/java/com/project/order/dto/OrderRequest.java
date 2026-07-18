package com.project.order.dto;

import lombok.Data;

@Data
public class OrderRequest {

    private String orderId;
    private Long userId;
    private String productName;
    private Integer quantity;
    private Double price;
    private String status;

}