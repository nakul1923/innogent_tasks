package com.innogent.ecommerce.mapper;

import com.innogent.ecommerce.dto.ProductDto;
import com.innogent.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product){

        if(product == null)return null;

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public Product toEntity(ProductDto productDto){

        if(productDto == null)return null;

        Product product = new Product();
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setImageUrl(productDto.getImageUrl());

        return product;
    }
}
