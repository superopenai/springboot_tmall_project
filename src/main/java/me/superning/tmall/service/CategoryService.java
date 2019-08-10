package me.superning.tmall.service;

import java.util.Iterator;
import java.util.List;


import me.superning.tmall.dao.CategoryDAO;
import me.superning.tmall.pojo.Category;
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
        Page pageFromJPA =  categoryDAO.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    public void add(Category category) {
        categoryDAO.save(category);

    }
	public void delete(int id)
	{
		categoryDAO.deleteById(id);
	}
    public Category get(int id) {
        Category c= categoryDAO.findById(id);
        return c;
    }

    public void update(Category bean) {
        categoryDAO.save(bean);

    }
}
