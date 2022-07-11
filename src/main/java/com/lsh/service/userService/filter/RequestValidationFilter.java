package com.lsh.service.userService.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现定制化的安全性控制
 * 从 HttpServletRequest 对象的请求头中获取了“SecurityFlag”标志位，
 * 否则将直接抛出一个 400 Bad Request 响应结果。
 *
 *如果我们想要实现定制化的安全性控制策略，就可以实现类似前面介绍的 RequestValidationFilter
 * 这样的过滤器，并放置在 BasicAuthenticationFilter 前。
 * 这样，在执行用户认证之前，我们就可以排除掉一批无效请求，
 *
 */
public class RequestValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        String requestId=httpServletRequest.getHeader("SecurityFlag");
        if (requestId==null||requestId.isEmpty()){
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return ;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
