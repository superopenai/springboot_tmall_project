package me.superning.tmall.dao;

import me.superning.tmall.pojo.Category;

import org.springframework.data.domain.Pageable;

import me.superning.tmall.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {
  Page<Product> findByCategory(Category category, Pageable pageable);
  Product findAllById(int id);


}
