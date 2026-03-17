package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
