package com.zhuima.stage2.controller;

import com.zhuima.stage2.domain.User;
import com.zhuima.stage2.form.UserForm;
import com.zhuima.stage2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        // 这快是new， 怎么会有数据呢
        model.addAttribute("userForm", new UserForm());
        return "register";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/register")
    public String registerPost(@Valid UserForm userForm, BindingResult result) {

        if (!userForm.confirmPassword()) {
            result.rejectValue( "confirmPassword",  "confirmError", "两次密码不一致");
        }

        if (result.hasErrors()) {
            return "register";
        }

        User user = userForm.convertToUser();
        userService.saveUser(user);
        return "redirect:/login";
    }



    @GetMapping("/exception")
    public String testException() {
        throw new RuntimeException("Not implemented");
    }

}
