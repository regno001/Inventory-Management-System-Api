package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.AddProductReqDto;
import com.example.InventoryManagementSystem.dto.ProductDto;

import java.util.List;

public interface ProductService {
ProductDto createProduct(AddProductReqDto request);

ProductDto getProduct(Long id);

List<ProductDto> getAllProduct();

void deleteProduct(Long id);

ProductDto updateProduct(Long id, AddProductReqDto request);
}
