package me.superning.tmall.service;

import me.superning.tmall.dao.PropertyDao;
import me.superning.tmall.pojo.Category;
import me.superning.tmall.pojo.Property;
import me.superning.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
@Autowired
    PropertyDao propertyDao;
@Autowired
    CategoryService categoryService;


    public Page4Navigator<Property> list(int cid, int start, int size,int navigatePages) {
        Category category = categoryService.get(cid);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable =PageRequest.of(start, size, sort);

        Page<Property> pageFromJPA =propertyDao.findByCategory(category,pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }

    public void add(Property bean) {
        propertyDao.save(bean);
    }
    public void  delete(int id)
    {
        propertyDao.deleteById(id);
    }
    public Property get(int id)
    {
      return   propertyDao.findById(id);

    }
    public void  update(Property property)
    {
        propertyDao.save(property);

    }

    public List<Property> listByCategory(Category category){
        return propertyDao.findByCategory(category);
    }



}
