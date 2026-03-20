package com.example.InventoryManagementSystem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddProductReqDto {

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    @Min(value = 100, message = "price must be grater then 100")
    private Double price;

    @NotNull
    @Positive
    private Integer quantity;

}
