package org.mall.controller;

import com.github.pagehelper.PageInfo;
import org.mall.domain.User;
import org.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }


    @GetMapping("/testPageHelper1")
    @ResponseBody
    public PageInfo<User> testPageHelper1(){
        PageInfo<User> queryResult = userService.findAllUserByPageS(1, 2);
        return queryResult;
    }

    @GetMapping("/testPageHelper2")
    @ResponseBody
    public List<User> testPageHelper2(){
        List<User> queryResult = userService.findAllUserByPageF(1, 2);
        return queryResult;
    }
}
