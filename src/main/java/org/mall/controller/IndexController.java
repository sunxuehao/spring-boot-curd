package org.mall.controller;

import org.mall.domain.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Autowired
    private Resource resource;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public void index(HttpServletResponse response) throws Exception{
        response.sendRedirect("/commodity/findAll");
    }

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    @ResponseBody
    public Resource getResource(){
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource,bean);
        return bean;
    }

    @RequestMapping(value = "/th", method = RequestMethod.GET)
    public String toThymeleaf(ModelMap map){
        map.addAttribute("name","sunxuehao");
        return "thymeleaf/thymeleaf";
    }


    @RequestMapping(value = "/err", method = RequestMethod.GET)
    public String error(){
        int i = 9/0;
        return "thymeleaf/thymeleaf";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteUser(@RequestParam("idArr") String idArr) {
        System.out.println(123);
//        System.out.println(idArr[0]);
        System.out.println(idArr);
//        for (Integer id : idArr) {
//            guiUserService.deleteListById(id);
//        }
        ModelMap modelMap = new ModelMap();
//        return this.getView("/guiUser/index", modelMap);
        return idArr;
    }


}
