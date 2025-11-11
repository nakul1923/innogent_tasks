package com.innogent.ecommerce.dto;

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
public class OrderResponseDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;
    private String address;
    private List<OrderItemResponseDto> items;
}
