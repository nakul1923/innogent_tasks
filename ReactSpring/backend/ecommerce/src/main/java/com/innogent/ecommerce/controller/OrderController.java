package com.innogent.ecommerce.controller;

import com.innogent.ecommerce.dto.OrderRequestDto;
import com.innogent.ecommerce.dto.OrderResponseDto;
import com.innogent.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){

        OrderResponseDto response = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(){

        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id){

        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
