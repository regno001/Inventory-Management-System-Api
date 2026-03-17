package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.AddProductReqDto;
import com.example.InventoryManagementSystem.dto.ProductDto;
import com.example.InventoryManagementSystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody AddProductReqDto request){
        ProductDto product = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto>  product = productService.getAllProduct();

        return ResponseEntity.ok(product);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductByID(@PathVariable Long id){
        ProductDto product = productService.getProduct(id);

        return ResponseEntity.ok(product);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

        return ResponseEntity.ok("Product Deleted Successfully");
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,@RequestBody AddProductReqDto request){
        ProductDto product = productService.updateProduct(id,request);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
