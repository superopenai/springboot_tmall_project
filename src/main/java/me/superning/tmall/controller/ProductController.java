package me.superning.tmall.controller;

import me.superning.tmall.pojo.Product;
import me.superning.tmall.service.CategoryService;
import me.superning.tmall.service.ProductImageService;
import me.superning.tmall.service.ProductService;
import me.superning.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    @GetMapping("/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable("cid") int cid,
                                        @RequestParam(value = "start", defaultValue = "0") int start,
                                        @RequestParam(value = "size", defaultValue = "5") int size)
            throws Exception
    {
        /**
         *
         */
        Page4Navigator<Product> page =productService.list(cid, start, size,5 );

        productImageService.setFirstProdutImages(page.getContent());

        return page;

    }
    @PostMapping("/products")
    public void  add(@RequestBody Product product)
    {
        productService.add(product);
    }
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") int id)
    {
        productService.delete(id);
    }
    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id") int id)
    {
        return  productService.get(id);
    }
    @PutMapping("/products")
    public Object update(@RequestBody Product bean) throws Exception {
        productService.update(bean);
        return bean;
    }






}
