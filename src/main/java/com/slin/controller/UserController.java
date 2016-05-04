package com.slin.controller;

import com.slin.pojo.User;
import com.slin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author SongLin.Yang
 * @data 2016-05-04 10:09
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/getUserById")
    public ModelAndView getUserById(){
        ModelAndView mv = new ModelAndView();
        User user = userService.getUser(1);
        mv.addObject("user",user);
        mv.setViewName("index");
        return mv;
    }
}
