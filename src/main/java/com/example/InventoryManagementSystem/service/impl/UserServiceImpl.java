package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.entity.User;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.exception.UserNotFound;
import com.example.InventoryManagementSystem.repository.UserRepository;
import com.example.InventoryManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByid(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFound("User Not found with id "+ id));
    }

    @Override
    public void deleteUser(Long id) {
     userRepository.deleteById(id);
    }
}
