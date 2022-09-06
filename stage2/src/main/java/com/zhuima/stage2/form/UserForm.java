package com.zhuima.stage2.form;

import com.zhuima.stage2.domain.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.*;

@Data
public class UserForm {

    private static final String REGEXP_PHONE = "^1[3456789]\\d{9}$";


    @NotEmpty(message = "用户名称不能为空")
    private String username;
    @Email(message = "邮箱地址不合法")
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    @Size(min = 6, max = 16, message = "密码长度必须在{min}-{max}之间")
    private String password;
    @Size(min = 11, max = 11, message = "手机号不合法")
    @Pattern(message = "手机号不合法", regexp = REGEXP_PHONE )
    private String phone;
    @Size(min = 6, max = 16, message = "密码长度必须在{min}-{max}之间")
    private String confirmPassword;



    public boolean confirmPassword() {

        if (this.getPassword().equals(this.getConfirmPassword())) {
            return true;
        }
        return false;
    }


    public User convertToUser( ) {
        User user = new UserFormConvert().convert(this);
        return user;
    }

    private class UserFormConvert implements FormConvert<UserForm, User>{

        @Override
        public User convert(UserForm userForm){
            User user = new User();
            BeanUtils.copyProperties(userForm, user);
            return user;
        }

    }

}
