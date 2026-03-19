package com.example.InventoryManagementSystem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
private Long userId;
@Valid
@NotEmpty(message = "At least one item should be selected")
private List<OrderItemDto> items;

}
