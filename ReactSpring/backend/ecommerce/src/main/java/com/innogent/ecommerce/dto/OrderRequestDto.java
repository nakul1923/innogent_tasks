package com.innogent.ecommerce.dto;

import com.innogent.ecommerce.entity.OrderItem;
import com.innogent.ecommerce.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {

    private List<OrderItemRequestDto> items;
    private Double totalAmount;
    private String address;
}
