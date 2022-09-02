package com.zhuima.stage2.form;

import com.zhuima.stage2.domain.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserForm {
    private String username;
    private String email;
    private String password;
    private String phone;
    private String confirmPassword;


    public User convertToUser( ) {
        User user = new UserFormConvert().convert(this);
        return user;
    }

    public class UserFormConvert implements FormConvert<UserForm, User>{

        @Override
        public User convert(UserForm userForm){
            User user = new User();
            BeanUtils.copyProperties(user, user);
            return user;
        }

    }

}
