package me.superning.tmall.dao;

import me.superning.tmall.pojo.Product;
import me.superning.tmall.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageDao extends JpaRepository<ProductImage,Integer> {
    
    ProductImage findById(int id);

    List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type_single);
}
