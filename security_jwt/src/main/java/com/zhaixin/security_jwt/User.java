package com.zhaixin.security_jwt;

import lombok.Data;

/**
 * @Program: security_jwt
 * @Classname: User
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/02/02 12:23
 */
@Data
public class User {
    private String username;
    private String password;
    private String role;
}

