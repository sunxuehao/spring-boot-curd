package org.mall.controller;

import com.github.pagehelper.PageInfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.mall.domain.User;
import org.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "check", method = RequestMethod.GET)
    public String check(@RequestParam("user_id") String id, @RequestParam("user_password") String password,
                        HttpServletResponse response) throws IOException {

        User user = userService.findById(Integer.valueOf(id));
        if (user.getPassword().equals(password)) {
            response.sendRedirect("/commodity/findAll");
            return null;
        } else {
            return "user/login";
        }
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }


    @GetMapping("/testPageHelper1")
    @ResponseBody
    public PageInfo<User> testPageHelper1() {
        PageInfo<User> queryResult = userService.findAllUserByPageS(1, 2);
        return queryResult;
    }

    @GetMapping("/testPageHelper2")
    @ResponseBody
    public List<User> testPageHelper2() {
        List<User> queryResult = userService.findAllUserByPageF(1, 2);
        return queryResult;
    }
}
