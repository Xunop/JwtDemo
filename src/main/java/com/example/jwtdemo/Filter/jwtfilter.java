package com.example.jwtdemo.Filter;

import com.auth0.jwt.interfaces.Claim;
import com.example.jwtdemo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 过滤器
 * @Author xun
 * @create 2022/4/29 14:19
 */
@Component
@WebFilter("/*")
@Slf4j
public class jwtfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.error("执行");

        String url = request.getRequestURI();
        if(url.contains("/login")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //获取token
        String token = request.getHeader("authorization");

        Map<String, Claim> userData = JwtUtil.valifyToken(token);

        if(userData == null){
            response.getWriter().write("token不合法");
            System.out.println("token不合法");
            return;
        }

        Integer id = userData.get("id").asInt();
        String name = userData.get("name").asString();
        Integer age = userData.get("age").asInt();
        //拿到用户信息之后放到request中
        request.setAttribute("id", id);
        request.setAttribute("name",name);
        request.setAttribute("age",age);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("终止");
        javax.servlet.Filter.super.destroy();
    }
}
