package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.OrderRequestDto;
import com.example.InventoryManagementSystem.dto.OrderResponseDto;
import com.example.InventoryManagementSystem.entity.*;
import com.example.InventoryManagementSystem.repository.OrderItemRepository;
import com.example.InventoryManagementSystem.repository.OrderRepository;
import com.example.InventoryManagementSystem.repository.ProductRepository;
import com.example.InventoryManagementSystem.repository.UserRepository;
import com.example.InventoryManagementSystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;


    @Override
    public OrderResponseDto createOrder(OrderRequestDto request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not Found"));
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        int totalPrice = 0;

        order = orderRepository.save(order);

        for (var itemDto : request.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < itemDto.getQuantity()) {
                throw new RuntimeException("Insufficient Stock");
            }

            product.setQuantity(product.getQuantity() - itemDto.getQuantity());
            productRepository.save(product);

            OrderItem item = new OrderItem();

            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(product.getPrice());

            orderItemRepository.save(item);

            totalPrice += product.getPrice() * itemDto.getQuantity();

        }
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(order.getId());
        response.setUserId(user.getId());
        response.setTotalPrice(totalPrice);
        response.setStatus(order.getStatus().name());
        response.setCreatedAt(order.getCreatedAt());
        return response;

    }
}
