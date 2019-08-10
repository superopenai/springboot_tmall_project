package me.superning.tmall.dao;

import me.superning.tmall.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {


    Order findByid(int oid);
}
