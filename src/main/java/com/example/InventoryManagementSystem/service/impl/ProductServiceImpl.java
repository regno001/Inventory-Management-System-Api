package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.AddProductReqDto;
import com.example.InventoryManagementSystem.dto.ProductDto;
import com.example.InventoryManagementSystem.entity.Product;
import com.example.InventoryManagementSystem.repository.ProductRepository;
import com.example.InventoryManagementSystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
private final ProductRepository productRepository;
private final ModelMapper modelMapper;



    public ProductDto createProduct(AddProductReqDto request){
    Product product = new Product();

    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setQuantity(request.getQuantity());

    product= productRepository.save(product);

    ProductDto dto = new ProductDto();

    dto.setId(product.getId());
    dto.setName(product.getName());
    dto.setDescription(product.getDescription());
    dto.setPrice(product.getPrice());
     dto.setQuantity(product.getQuantity());

     return dto;
  }



    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        ProductDto dto = new ProductDto();

        dto.setId(product.getId());
        dto.setName(product.getName());

        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());

        return dto;


    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream().map(product->{
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setDescription(product.getDescription());
            dto.setQuantity(product.getQuantity());
            return  dto;


        }).toList();
    }


    @Override
    public void deleteProduct(Long id) {
      productRepository.deleteById(id);
    }

    @Override
    public ProductDto updateProduct(Long id, AddProductReqDto request) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not Found"));

        if(request.getName()!=null){
            product.setName(request.getName());
        }if(request.getPrice()!=null){
            product.setPrice(request.getPrice());

        }if(request.getQuantity()!=null){
            product.setQuantity(request.getQuantity());

        }
        productRepository.save(product);

        return modelMapper.map(product,ProductDto.class);
    }


}
