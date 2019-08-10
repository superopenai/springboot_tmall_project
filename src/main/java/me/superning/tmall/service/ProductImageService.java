package me.superning.tmall.service;


import me.superning.tmall.dao.ProductImageDao;
import me.superning.tmall.pojo.Product;
import me.superning.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService   {

    public static final String type_single = "single";
    //单个图片
    public static final String type_detail = "detail";
    //详情图片
    @Autowired ProductImageDao productImageDao;
    @Autowired ProductService productService;

    public void add(ProductImage bean) {
        productImageDao.save(bean);

    }
    public void delete(int id) {
        productImageDao.deleteById(id);
    }

    public ProductImage get(int id) {

        return productImageDao.findById(id);
    }

    public List<ProductImage> listSingleProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_single);
    }
    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_detail);
    }

    public void setFirstProdutImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product);
        if(!singleImages.isEmpty()) {
            product.setFirstProductImage(singleImages.get(0));
        } else {
            product.setFirstProductImage(new ProductImage());
            //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。
        }

    }
    public void setFirstProdutImages(List<Product> products) {
        for (Product product : products) {
            setFirstProdutImage(product);
        }
    }
}