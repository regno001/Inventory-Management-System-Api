package com.example.InventoryManagementSystem.service;


import com.example.InventoryManagementSystem.dto.OrderRequestDto;
import com.example.InventoryManagementSystem.dto.OrderResponseDto;

public interface OrderService {
OrderResponseDto createOrder(OrderRequestDto request);

}
