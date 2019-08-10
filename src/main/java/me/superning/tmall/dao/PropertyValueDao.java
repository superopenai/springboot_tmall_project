package me.superning.tmall.dao;

import me.superning.tmall.pojo.Product;
import me.superning.tmall.pojo.Property;
import me.superning.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * public interface JpaRepository<T, ID> 这个ID是主键的类型
 */
public interface PropertyValueDao extends JpaRepository<PropertyValue,Integer> {
    PropertyValue getByPropertyAndProduct(Property property, Product product);

    List<PropertyValue> findByProductOrderByIdDesc(Product product);
}
