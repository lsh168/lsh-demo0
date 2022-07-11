package com.lsh.controller;


import com.lsh.pojo.entity.User;
import com.lsh.domain.model.R;
import com.lsh.pojo.VO.UserVO;
import com.lsh.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author ALE
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    R register(User user){
        User u = userService.createUser(user);
        return new R(200,"注册成功！",u);
    }
    @PostMapping("login")
    R login(UserVO userVO) throws Throwable {
        userService.auth(userVO);
        return new R(200,"登陆成功！");
    }


}
