package com.zhaixin.security_jwt.service;

import com.zhaixin.security_jwt.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: security_jwt
 * @Classname: MyUserDetailsService
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/02/02 12:19
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查找用户信息
        User user = userService.findUserByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException(username + "Not found");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role = user.getRole();
        String[] roles = role.split(",");
        for (String s : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(s);
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(),grantedAuthorities);
    }
}

