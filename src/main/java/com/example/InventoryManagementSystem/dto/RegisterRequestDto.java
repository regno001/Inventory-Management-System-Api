package com.example.InventoryManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @NotBlank(message= "name is required")
    private String name;

    @Email(message= "Invalid email")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message="Password is Required")
    private String password;

}
