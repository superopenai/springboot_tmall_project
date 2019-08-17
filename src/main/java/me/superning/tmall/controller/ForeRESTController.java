package me.superning.tmall.controller;

import me.superning.tmall.pojo.Category;
import me.superning.tmall.pojo.User;
import me.superning.tmall.service.CategoryService;
import me.superning.tmall.service.ProductService;
import me.superning.tmall.service.UserService;
import me.superning.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ForeRESTController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/forehome")
    public Object home() {
        List<Category> cs= categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
        categoryService.removeCategoryFromProduct(cs);
        return cs;
    }


    @PostMapping("/foreregister")
    public Object register(@RequestBody User user)
    {
        String name =  user.getName();
        String password = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        boolean ishave = userService.have(name);
        if (ishave)
        {
            String message ="用户名已经被使用,不能使用";
            return Result.fail(message);
        }
        user.setPassword(password);

        userService.add(user);
        return Result.success();
    }

    @PostMapping("/forelogin")
    public Object login(@RequestBody User userParam, HttpSession session) {
        String name =  userParam.getName();
        name = HtmlUtils.htmlEscape(name);

        User user =userService.userLogin(name,userParam.getPassword());
        if(null==user){
            String message ="账号密码错误";
            return Result.fail(message);
        }
        else{
            session.setAttribute("user", user);
            return Result.success();
        }
    }
}