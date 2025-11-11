package com.innogent.ecommerce.mapper;
import com.innogent.ecommerce.dto.OrderItemResponseDto;
import com.innogent.ecommerce.entity.OrderItem;

public class OrderItemMapper {

    public static OrderItemResponseDto toDto(OrderItem item) {
        if (item == null) return null;

        return OrderItemResponseDto.builder()
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }

}
