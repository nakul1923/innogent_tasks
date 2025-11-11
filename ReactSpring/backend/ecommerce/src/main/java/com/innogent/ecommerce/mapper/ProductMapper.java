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
}
