package me.superning.tmall.service;

import java.util.Iterator;
import java.util.List;


import me.superning.tmall.dao.CategoryDAO;
import me.superning.tmall.pojo.Category;
import me.superning.tmall.pojo.Product;
import me.superning.tmall.util.Page4Navigator;
import org.junit.experimental.categories.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public Page4Navigator<Category> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page pageFromJPA = categoryDAO.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    public void add(Category category) {
        categoryDAO.save(category);

    }

    public void delete(int id) {
        categoryDAO.deleteById(id);
    }

    public Category get(int id) {
        Category c = categoryDAO.findById(id);
        return c;
    }

    public void update(Category bean) {
        categoryDAO.save(bean);

    }

    /**
     *
     * @param categories
     * 这个方法的用处是删除Product对象上的 分类。
     * 为什么要删除呢？ 因为在对分类做序列还转换为 json 的时候，会遍历里面的 products, 然后遍历出来的产品上，又会有分类，
     */
    public void removeCategoryFromProduct(List<Category> categories) {
        categories.forEach(category -> {
            removeCategory(category);
        });
    }

    public void removeCategory(Category category) {
        List<Product> products = category.getProducts();
        if (products != null) {
            products.forEach(product -> {
                product.setCategory(null);
            });
        }
        List<List<Product>> productbyRow = category.getProductsbyRow();
        if (null!=productbyRow)
        {
            productbyRow.forEach(products1 -> {
                products1.forEach(product -> {
                    product.setCategory(null);
                });
            });
        }
    }
}
