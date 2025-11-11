package com.innogent.ecommerce.service;

import com.innogent.ecommerce.dto.OrderItemResponseDto;
import com.innogent.ecommerce.dto.OrderRequestDto;
import com.innogent.ecommerce.dto.OrderResponseDto;
import com.innogent.ecommerce.entity.Order;
import com.innogent.ecommerce.entity.OrderItem;
import com.innogent.ecommerce.entity.Product;
import com.innogent.ecommerce.entity.enums.OrderStatus;
import com.innogent.ecommerce.mapper.OrderMapper;
import com.innogent.ecommerce.repository.OrderRepository;
import com.innogent.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Scheduled(fixedRate = 3600000) // runs every 1 hour
    public void autoDeliverOrders() {
        List<Order> pendingOrders = orderRepository.findByStatus(OrderStatus.PENDING);

        LocalDateTime now = LocalDateTime.now();
        for (Order order : pendingOrders) {
            if (order.getOrderDate().plusHours(6).isBefore(now)) {
                order.setStatus(OrderStatus.DELIVERED);
                orderRepository.save(order);
            }
        }
    }

    public OrderResponseDto createOrder(OrderRequestDto requestDto){

        Order order = new Order();

        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(requestDto.getTotalAmount());
        order.setAddress(requestDto.getAddress());

        List<OrderItem> orderItems = requestDto.getItems().stream().map(itemDto->{

            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(()->new RuntimeException("Product not found with this id :" + itemDto.getProductId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(itemDto.getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(orderItems);

        orderRepository.save(order);

        OrderResponseDto response = new OrderResponseDto();

        response.setOrderId(order.getId());
        response.setOrderDate(order.getOrderDate());
        response.setStatus(order.getStatus().name());
        response.setTotalAmount(order.getTotalAmount());
        response.setAddress(order.getAddress());

        List<OrderItemResponseDto> itemResponses = orderItems.stream().map(item->{

            OrderItemResponseDto dto = new OrderItemResponseDto();

            dto.setProductName(item.getProduct().getName());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getPrice());
            return dto;
        }).collect(Collectors.toList());

        response.setItems(itemResponses);
        return response;
    }

    public List<OrderResponseDto> getAllOrders(){

        return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
    }

    public OrderResponseDto getOrderById(Long id){

        Order order = orderRepository.getOrderById(id).orElseThrow(()->new RuntimeException("No Order found with this id: " + id));

        return orderMapper.toDto(order);


    }


}
