package me.superning.tmall.controller;

import me.superning.tmall.pojo.User;
import me.superning.tmall.service.UserService;
import me.superning.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

@Autowired
    UserService userService;


@GetMapping("/users")

    public Page4Navigator<User> list (@RequestParam(value = "start" ,defaultValue = "0") int start,
                                      @RequestParam(value = "size",defaultValue = "5") int size
                                      )
{
          Page4Navigator<User> page=  userService.list(start,size,5);
            return  page;

}
}
