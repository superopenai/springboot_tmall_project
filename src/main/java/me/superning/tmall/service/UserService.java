package me.superning.tmall.service;

import me.superning.tmall.dao.UserDAO;
import me.superning.tmall.pojo.User;
import me.superning.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User userLogin(String name,String password)
    {
       return userDAO.getByNameAndPassword(name,password);

    }



    public User getUser(String name)
    {
       return userDAO.findByName(name);

    }
    public boolean have(String name)
    {
        User user = getUser(name);

        return (user!=null);

    }

    public Page4Navigator<User> list(int start,int size ,int pages)
    {
        Pageable pageable = PageRequest.of(start,size, Sort.Direction.DESC,"id");
       Page jpa= userDAO.findAll(pageable);
        return new Page4Navigator<>(jpa,pages);
    }

    public void  add(User user)
    {
        userDAO.save(user);

    }

}
