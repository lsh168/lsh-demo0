package com.lsh.service.userService;

import com.alibaba.fastjson.JSON;
import com.lsh.pojo.entity.User;
import com.lsh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CustomUserDetailsManager implements UserDetailsManager {
    @Autowired
    private UserRepository userRepository;
    /**
     *
     * UserDetailsManager继承了UserDetailsService接口
     *
     *
     *      * UserDetailsService：定义了对 UserDetails 的查询操作。
     *      * UserDetailsManager：扩展 UserDetailsService，添加了创建用户、修改用户密码等功能。
     *      * UserDetails：描述 Spring Security 中的用户。
     *      * GrantedAuthority：定义用户的操作权限。
     * @param user
     */
    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        Optional<User> optionalUser = userRepository.findByUserName(username);

        if (optionalUser.isPresent()) {
            throw new RuntimeException("用户已存在！");
        }
        return false;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        System.out.printf(user.get().toString());

        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        }
        throw new UsernameNotFoundException("username"+username+"not found!");
    }
}
