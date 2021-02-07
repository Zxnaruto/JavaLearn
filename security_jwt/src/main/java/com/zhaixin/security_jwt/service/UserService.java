package com.zhaixin.security_jwt.service;

import com.zhaixin.security_jwt.User;

public interface UserService {
    User findUserByUserName(String username);
}
