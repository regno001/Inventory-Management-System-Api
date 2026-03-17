package com.example.InventoryManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
private Long orderId;

private Long userId;

private List<OrderItemDto> items;

private Integer totalPrice;

private String status;

private LocalDateTime createdAt;


}
