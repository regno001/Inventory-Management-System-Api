package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
