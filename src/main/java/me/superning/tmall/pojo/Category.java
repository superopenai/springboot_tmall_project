package me.superning.tmall.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "category")
//表名(严格区分大小写)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Category {

    @Id
//    主键使用这个注解
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    自增长策略
    @Column(name = "id")
    private int id;


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
