package me.superning.tmall.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.EAN;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author superning
 */
@Entity
@Table(name = "category")
//表名(严格区分大小写)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Category  implements Serializable {

    @Id
//    主键使用这个注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    自增长策略
    @Column(name = "id")
    private int id;

    @Transient
    private List<Product> products;

    @Transient
    private  List<List<Product>> productsbyRow;

    public List<Product>
    getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsbyRow() {
        return productsbyRow;
    }

    public void setProductsbyRow(List<List<Product>> productsbyRow) {
        this.productsbyRow = productsbyRow;
    }

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
