package me.superning.tmall.service;


import me.superning.tmall.dao.PropertyValueDao;
import me.superning.tmall.pojo.Product;
import me.superning.tmall.pojo.Property;
import me.superning.tmall.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueService {

    @Autowired
    PropertyValueDao propertyValueDao;
    @Autowired
    PropertyService propertyService;

    public void update(PropertyValue bean) {
        propertyValueDao.save(bean);
    }
    public void  initvalue (Product product)
    {
        List<Property> properties  = propertyService.listByCategory(product.getCategory());
        for (Property property : properties) {
            PropertyValue propertyValue = getByPropertyAndProduct(product, property);
            if(null==propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyValueDao.save(propertyValue);
            }
        }

    }

    public PropertyValue getByPropertyAndProduct(Product product, Property property) {
        return propertyValueDao.getByPropertyAndProduct(property,product);
    }

    public List<PropertyValue> list(Product product) {

        return propertyValueDao.findByProductOrderByIdDesc(product);
    }

}
