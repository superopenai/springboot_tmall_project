package me.superning.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "productimage")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})

public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * 前两行的意思：多对一单向
     * productimage表的pid--->product表的id
     * 第三个注解为了解决对象中存在双向引用导致的无限递归问题.
     */
    @ManyToOne
    @JoinColumn(name="pid")
    @JsonBackReference
    private Product product;

    private String type;

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", product=" + product +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
