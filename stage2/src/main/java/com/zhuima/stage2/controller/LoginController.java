package com.zhuima.stage2.controller;

import com.zhuima.stage2.domain.User;
import com.zhuima.stage2.form.UserForm;
import com.zhuima.stage2.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/register")
    public String registerPost(UserForm userForm) {
        User user = userForm.convertToUser();
        userService.saveUser(user);
        return "redirect:/login";
    }



}
