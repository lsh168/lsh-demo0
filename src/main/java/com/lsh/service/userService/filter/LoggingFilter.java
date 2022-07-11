package com.lsh.service.userService.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class LoggingFilter implements Filter {
//    private final Logger logger=Logger.getLogger(Authenti)
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest=(HttpServletRequest) servletRequest;
        String uniqueRequestId = httpRequest.getHeader("UniqueRequestId");


    }
}
