package com.lsh.config;

import com.lsh.service.userService.AuthenticationProviderService;
import com.lsh.service.userService.CustomUserDetailsManager;
import com.lsh.service.userService.filter.RequestValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthenticationProviderService authenticationProviderService;
    @Autowired
    private CustomUserDetailsManager CustomUserDetailsManager;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //关闭防御csrf功能，采用token解决
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //        必须有过滤器，不然不返回结果。
//        http.exceptionHandling()
//                .accessDeniedHandler()
//        整个 Spring Security 过滤器链的末端是一个 FilterSecurityInterceptor，
//        它本质上也是一个 Filter。它的核心功能是实现权限控制
//                .addFilterBefore(new RequestValidationFilter(),
//                        UsernamePasswordAuthenticationFilter.class)
                //对所有访问 HTTP 端点的 HttpServletRequest 进行限制；
                .authorizeRequests()
                // 对于登录接口 允许匿名访问   /user/register  前面必须加“/”，否则不生效
                .antMatchers("/user/login","/user/register").anonymous()

                // .anyRequest().authenticated()  必须在 .antMatchers("/user/login").anonymous()之后，否则会报错误。
                //对于所有请求都需要执行认证，也就是对没有通过认证的用户就无法访问任何端点；
                .anyRequest().authenticated()
                .and()
                //自定义的一些东西配置认证器
                .authenticationProvider(authenticationProviderService)
                .userDetailsService(CustomUserDetailsManager)
                //允许跨域
                .cors()
                .and()
                .build();
//        必须有过滤器，不然不返回结果。
//        http.exceptionHandling()
//                .accessDeniedHandler()
//        整个 Spring Security 过滤器链的末端是一个 FilterSecurityInterceptor，
//        它本质上也是一个 Filter。它的核心功能是实现权限控制


    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * FilterSecurityInterceptor 的拦截器，它位于整个过滤器链的末端，
     * 核心功能是对权限控制过程进行拦截.
     * 以此判定该请求是否能够访问目标 HTTP 端点。
     *
     * SecurityMetadataSource 接口，该接口保存着一系列安全元数据的数据源，代表权限配置的抽象。
     *
     *  AccessDecisionManager 接口中，又把具体的决策过程委托给了 AccessDecisionVoter 接口。
     *  AccessDecisionVoter 可以被认为是一种投票器，负责对授权决策进行表决。
     */




}
