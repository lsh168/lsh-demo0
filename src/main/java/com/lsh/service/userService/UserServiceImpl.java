package com.lsh.service.userService;

import com.lsh.pojo.entity.User;
import com.lsh.pojo.VO.UserVO;
import com.lsh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    @Override
    public User createUser(User u) {
        customUserDetailsManager.userExists(u.getUserName());
        Optional.of(u.getUserName());
        Optional.of(u.getPassword());
        String password = bCryptPasswordEncoder.encode(u.getPassword());
        u.setPassword(password);
        User user = userRepository.save(u);
        return user;
    }

    /**
     * 认证用户名和密码
     *
     * @param userVO
     */
    @Override
    public void auth(UserVO userVO) throws Throwable {
        Optional.of(userVO.getUserName());
//        Object principal, Object credentials
//        username  认证主体
//        password  认证凭证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userVO.getUserName(), userVO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        Optional<Authentication> authenticationOptional = Optional.of(authenticate);
        authenticationOptional.orElseThrow(()->{throw new RuntimeException("Bad credentials.");});
        //如果通过认证，使用UserId生成JWT
    }
}
