package com.zhuima.stage2.service;

import com.zhuima.stage2.domain.User;
import com.zhuima.stage2.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 新增用户
     * @param user
     * @return
     */
    public User saveUser(User user) {

        return userRepository.save(user);
    }
}
