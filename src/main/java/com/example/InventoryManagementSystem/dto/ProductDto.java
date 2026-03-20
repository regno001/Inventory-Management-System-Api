package com.example.InventoryManagementSystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    @NotBlank(message = "Product name is Required")
    private String name;

    private String description;
    @NotNull(message = "Product price is required")
    @Positive(message = "Price must be greater Then 0")
    private Double price;
     @NotNull
     @Min(value = 0,message = "Quantity cannot be 0")
    private Integer quantity;
}
