package com.lsh.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;
    //为AuthenticationProviderService 提供注入

    /**
     *
     * 这里是客户端传进来的认证信息
     * @param authentication  认证对象 Authentication
     * @return  //执行认证，返回认证结果
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        CustomUserDetails user = customUserDetailsManager.loadUserByUsername(username);
        return checkPassword(user,password,bCryptPasswordEncoder);
    }

    /**
     *
     * @param user  用户信息
     * @param rawPassword  未加工的密码
     * @param passwordEncoder 密码加密类
     * @return
     */
    private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder passwordEncoder){
        //matches()  对提交的原始密码与库中存储的加密密码进行比对
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            //使用了 UsernamePasswordAuthenticationToken 来传递用户名和密码
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),user.getPassword(), user.getAuthorities());
        }else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    /**
     * 如果当前的AuthenticationProvider支持作为Authentication对象而提供的类型，
     * 则可以实现此方法以返回true。注意，即使该方法对一个对象返回true,authenticate()方法仍然
     * 有可能通过返回null来拒绝请求。Spring Security这样的设计是较为灵活的，
     * 使得我们可以实现一个AuthenticationProvider，它可以根据请求的详细信息来拒绝身份验证请求，
     * 而不仅仅是根据请求的类型来判断。
     *
     *
     * 确定新的AuthenticationProvider支持哪种类型的Authentication对象：
     *  重写supports(Class<?> authentication)方法以指定所定义的AuthenticationProvider支持哪种类型的身份验证。
     *  重写 authenticate(Authentication authentication)方法以实现身份验证逻辑。
     *
     *判断是否支持当前的认证对象
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        //判断当前的Class对象所表示的类，是不是参数中传递的Class对象所表示的类的父类，超接口，
        // 或者是相同的类型。是则返回true，否则返回false。
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
