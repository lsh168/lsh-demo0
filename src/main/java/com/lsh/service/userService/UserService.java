package com.lsh.service.userService;

import com.lsh.pojo.entity.User;
import com.lsh.pojo.VO.UserVO;


/**
 * @author ALE
 */
public interface UserService {
    /**
     * 创建用户，会判断用户是否存在
     * @param user
     * @return
     */
    User createUser(User user);
    /**
     * 认证用户名和密码
     */
    void auth(UserVO userVO) throws Throwable;

}
