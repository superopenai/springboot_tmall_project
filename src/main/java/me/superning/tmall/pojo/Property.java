package me.superning.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "property")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Property  {
    @Id
//    主键使用这个注解
@GeneratedValue(strategy = GenerationType.IDENTITY)
//    自增长策略
@Column(name = "id")
    private int id;



    @Column(name = "name")
    private  String name;


   @ManyToOne
   @JoinColumn(name = "cid")



   private Category category;
//category已经和cid联系在了一起
    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
