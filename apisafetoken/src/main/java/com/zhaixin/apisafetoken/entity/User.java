package com.zhaixin.apisafetoken.entity;

import lombok.Data;

/**
 * @Program: apisafetoken
 * @Classname: User
 * @Author:  Zhai
 * @Description:
 * @Date: 2021/06/09 16:09
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String password;
}

