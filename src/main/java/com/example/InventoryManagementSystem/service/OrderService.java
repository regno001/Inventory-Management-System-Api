package com.example.InventoryManagementSystem.service;


import com.example.InventoryManagementSystem.dto.OrderRequestDto;
import com.example.InventoryManagementSystem.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
OrderResponseDto createOrder(OrderRequestDto request);


List<OrderResponseDto> getAllOrderDetails();


    OrderResponseDto getOrderById(Long id);

    OrderResponseDto updateOrderStatus(Long id, String status);
}
