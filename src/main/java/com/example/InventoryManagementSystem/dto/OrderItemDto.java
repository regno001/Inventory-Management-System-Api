package com.example.InventoryManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItemDto {
private Long productId;
private String productName;
private Integer quantity;
private Double price;

}
