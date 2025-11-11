package com.innogent.ecommerce.service;

import com.innogent.ecommerce.dto.ProductDto;
import com.innogent.ecommerce.dto.PromocodeDto;
import com.innogent.ecommerce.entity.Product;
import com.innogent.ecommerce.mapper.ProductMapper;
import com.innogent.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public List<ProductDto> getAllProducts(){

        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    public ProductDto getProductById(Long id){

        Product product =  productRepository.findById(id).orElseThrow(()->new RuntimeException("No product found with this id " + id));

        return productMapper.toDto(product);
    }

}
