package com.zhaixin.springanoncation.controller;

import com.zhaixin.springanoncation.req.LoginReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Program: springanoncation
 * @Classname: Test
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/07/05 14:25
 */
@RestController
public class Test {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(@RequestBody @Valid LoginReq loginReq) {
        System.out.println(loginReq.getPassword()+loginReq.getUsername()+"============");
        return loginReq.toString();
    }

    @PostMapping("/test")
    public String userLoginPM(@RequestBody LoginReq loginReq) {
        System.out.println(loginReq.getPassword()+loginReq.getUsername()+"============");
        return loginReq.toString();
    }

    @RequestMapping(value = "/loginGet", method = RequestMethod.GET)
    public String userLoginGet(@RequestParam String username, @RequestParam String password) {
        System.out.println(username+password+"============");
        return username;
    }

    @RequestMapping(value = "/loginPath/{username}", method = RequestMethod.GET)
    public String userLoginGet(@PathVariable String username) {
        System.out.println(username+"============");
        return username;
    }
}

