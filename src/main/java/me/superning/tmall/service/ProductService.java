package me.superning.tmall.service;


import me.superning.tmall.dao.ProductDao;
import me.superning.tmall.pojo.Category;
import me.superning.tmall.pojo.Product;
import me.superning.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryService categoryService;

    public Page4Navigator<Product> list (int cid, int start, int size, int navigatePages)
    {
        Category category = categoryService.get(cid);
        Pageable pageable = PageRequest.of(start,size,Sort.Direction.DESC,"id");
        Page<Product> PagefromJpa = productDao.findByCategory(category,pageable);
        return  new Page4Navigator<>(PagefromJpa,navigatePages);
    }
    public void add(Product product)
    {
        productDao.save(product);

    }
    public void delete(int id)
    {
        productDao.deleteById(id);
    }
    public Product get(int id)
    {
      return productDao.findAllById(id);
    }


    public void update(Product product)
    {
        productDao.save(product);

    }


}
