package me.superning.tmall.dao;

import java.util.List;

import me.superning.tmall.pojo.Order;
import me.superning.tmall.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer>{
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
}