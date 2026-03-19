package com.example.InventoryManagementSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    @NotNull(message = "Product name is Required")
    private String name;

    private String description;
    @NotNull(message = "Product price is required")
    private Double price;

    private Integer quantity;
}
