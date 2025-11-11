package com.innogent.ecommerce.mapper;

import com.innogent.ecommerce.dto.OrderItemDto;
import com.innogent.ecommerce.dto.OrderItemResponseDto;
import com.innogent.ecommerce.entity.OrderItem;
import com.innogent.ecommerce.entity.Product;

public class OrderItemMapper {

    public static OrderItemResponseDto toDto(OrderItem item) {
        if (item == null) return null;

        return OrderItemResponseDto.builder()
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }

//    public OrderItem toEntity(OrderItemDto orderItemDto, Product product){
//
//        if(orderItemDto==null)return null;
//
//        OrderItem orderItem = new OrderItem();
//
//        orderItem.setPrice(orderItemDto.getPrice());
//        orderItem.setQuantity(orderItemDto.getQuantity());
//        orderItem.setProduct(product);
//
//        return orderItem;
//    }
}
