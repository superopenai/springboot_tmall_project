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

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;


    public Page4Navigator<Product> list(int cid, int start, int size, int navigatePages) {
        Category category = categoryService.get(cid);
        Pageable pageable = PageRequest.of(start, size, Sort.Direction.DESC, "id");
        Page<Product> PagefromJpa = productDao.findByCategory(category, pageable);
        return new Page4Navigator<>(PagefromJpa, navigatePages);
    }

    public void add(Product product) {
        productDao.save(product);

    }

    public void delete(int id) {
        productDao.deleteById(id);
    }

    public Product get(int id) {
        return productDao.findAllById(id);
    }

    public void update(Product product) {
        productDao.save(product);

    }

    /**
     *
     * @param categorys 各种分类组成一个list
     *                  根据增强循环遍历每一个分类
     */
    public void fill(List<Category> categorys) {
        for (Category category : categorys) {
            fill(category);
        }
    }

    /**
     *
     * @param category 根据得到的每一个分类，调用ProductDao的方法，取得这个分类下所有的产品
     *                 然后给这个分类的 products 赋值。
     */
    public void fill(Category category) {
        List<Product> products = listByCategory(category);
        productImageService.setFirstProdutImages(products);
        category.setProducts(products);
    }


    public void fillByRow(List<Category> categorys) {
        int productNumberEachRow = 8;
        for (Category category : categorys) {
            List<Product> products =  category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsbyRow(productsByRow);
        }
    }

    public List<Product> listByCategory(Category category){
        return productDao.findByCategoryOrderById(category);
    }


}
