package com.innogent.ecommerce.mapper;


import com.innogent.ecommerce.dto.OrderItemResponseDto;
import com.innogent.ecommerce.dto.OrderRequestDto;
import com.innogent.ecommerce.dto.OrderResponseDto;
import com.innogent.ecommerce.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderResponseDto toDto(Order order) {
        if (order == null) return null;

        List<OrderItemResponseDto> itemDtos = order.getItems() != null
                ? order.getItems()
                .stream()
                .map(OrderItemMapper::toDto)
                .collect(Collectors.toList())
                : List.of();

        return OrderResponseDto.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus().name())
                .totalAmount(order.getTotalAmount())
                .address(order.getAddress())
                .items(itemDtos)
                .build();
    }

//    public Order toEntity(OrderRequestDto orderRequestDto){
//
//        if(orderRequestDto ==null)return null;
//
//        Order order = new Order();
//
//        order.setId(orderRequestDto.getId());
//        order.setOrderDate(orderRequestDto.getOrderDate());
//        order.setAddress(orderRequestDto.getAddress());
//        order.setStatus(orderRequestDto.getStatus());
//        order.setItems(orderRequestDto.getItems());
//        order.setTotalAmount(orderRequestDto.getTotalAmount());
//
//        return order;
//    }
}
