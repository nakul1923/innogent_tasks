package com.innogent.ecommerce.dto;

import com.innogent.ecommerce.entity.Order;
import com.innogent.ecommerce.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private Long productId;
    private Integer quantity;
    private Double price;
    private String ProductName;

}
