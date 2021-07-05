package com.zhaixin.springanoncation.req;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Program: springanoncation
 * @Classname: UserReq
 * @Author: Zhai
 * @Description:
 * @Date: 2021/07/05 14:23
 */
@Data
public class LoginReq {
    @NotBlank(message = "username not null")
    private String username;
    @NotBlank(message = "password not null")
    private String password;
}

