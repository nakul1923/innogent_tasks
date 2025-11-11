package com.innogent.ecommerce.repository;

import com.innogent.ecommerce.entity.Order;
import com.innogent.ecommerce.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> getOrderById(Long id);

    List<Order> findByStatus(OrderStatus status);
}
