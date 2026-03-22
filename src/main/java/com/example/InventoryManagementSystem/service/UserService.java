package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getUserByid(Long id);

    void deleteUser(Long id);

}
