package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.OrderItemDto;
import com.example.InventoryManagementSystem.dto.OrderRequestDto;
import com.example.InventoryManagementSystem.dto.OrderResponseDto;
import com.example.InventoryManagementSystem.entity.*;
import com.example.InventoryManagementSystem.exception.InsufficientStock;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.repository.OrderItemRepository;
import com.example.InventoryManagementSystem.repository.OrderRepository;
import com.example.InventoryManagementSystem.repository.ProductRepository;
import com.example.InventoryManagementSystem.repository.UserRepository;
import com.example.InventoryManagementSystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;


    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order = orderRepository.save(order);
        double totalPrice = 0;

        for (OrderItemDto itemDto : request.getItems()) {

            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            if (product.getQuantity() < itemDto.getQuantity()) {
                throw new InsufficientStock("Insufficient stock for product: " + product.getName());
            }

            product.setQuantity(product.getQuantity() - itemDto.getQuantity());
            productRepository.save(product);


            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(product.getPrice());
            order.getItem().add(item);
            orderItemRepository.save(item);
            totalPrice += product.getPrice() * itemDto.getQuantity();
        }


        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        return mapToResponse(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrderDetails() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return mapToResponse(order);
    }


    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        try{
            order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid Status");
        }

        orderRepository.save(order);
        return mapToResponse(order);
    }


    private OrderResponseDto mapToResponse(Order order) {

        OrderResponseDto dto = new OrderResponseDto();

        dto.setOrderId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus().name());
        dto.setCreatedAt(order.getCreatedAt());

        if (order.getItem() != null) {
            List<OrderItemDto> itemDtos = order.getItem().stream().map(item -> {
                OrderItemDto itemDto = new OrderItemDto();

                itemDto.setProductId(item.getProduct().getId());
                itemDto.setProductName(item.getProduct().getName());
                itemDto.setQuantity(item.getQuantity());
                itemDto.setPrice(item.getPrice());

                return itemDto;
            }).toList();

            dto.setItems(itemDtos);
        }

        return dto;
    }
}