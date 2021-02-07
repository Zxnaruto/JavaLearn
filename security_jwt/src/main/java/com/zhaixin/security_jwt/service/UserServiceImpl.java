package com.zhaixin.security_jwt.service;

import com.zhaixin.security_jwt.User;
import org.springframework.stereotype.Service;

/**
 * @Program: security_jwt
 * @Classname: UserServiceImpl
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/02/02 12:25
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findUserByUserName(String username) {
        return null;
    }
}

