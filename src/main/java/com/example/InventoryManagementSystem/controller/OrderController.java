package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.OrderRequestDto;
import com.example.InventoryManagementSystem.dto.OrderResponseDto;
import com.example.InventoryManagementSystem.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto request){
        OrderResponseDto response = orderService.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrderDetail(){
        List<OrderResponseDto> response =orderService.getAllOrderDetails();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id){
        OrderResponseDto response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@PathVariable Long id,@RequestParam String status){
        OrderResponseDto response= orderService.updateOrderStatus(id,status);
        return ResponseEntity.ok(response);
    }
}
