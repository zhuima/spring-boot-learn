package com.zhuima.stage2.controller;

import com.zhuima.stage2.domain.User;
import com.zhuima.stage2.form.UserForm;
import com.zhuima.stage2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String register() {
        return "register";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/register")
    public String registerPost(@Valid UserForm userForm, BindingResult result) {
        boolean boo = true;

        if (!userForm.confirmPassword()) {
            result.rejectValue( "confirmPassword",  "confirmError", "两次密码不一致");
            boo = false;
        }



        if (result.hasErrors()) {
            List<FieldError> fieldErrors  = result.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                System.out.println(fieldError.getField() + " " + fieldError.getDefaultMessage() + " " + fieldError.getCode());
            }
            boo = false;
        }



        if (!boo) {
            return "register";
        }

        User user = userForm.convertToUser();
        userService.saveUser(user);
        return "redirect:/login";
    }



}
