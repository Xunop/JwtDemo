package com.example.jwtdemo.filter;

import com.auth0.jwt.interfaces.Claim;
import com.example.jwtdemo.Exception.LocalRuntimeException;
import com.example.jwtdemo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 过滤器
 * 继承
 *
 * @Author xun
 * @create 2022/4/29 14:19
 */
@Slf4j
public class Myfilter implements Filter {

    private String[] noFilters;

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("过滤器启动");
        // 初始化不过滤的路径
        String noFilter = filterConfig.getInitParameter("noFilter");
        if (noFilter != null && noFilter.length() > 0) {
            noFilters = noFilter.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        log.error("执行");

//        String url = request.getRequestURI();
//        if(url.contains("/login")){
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
        // 如果这个路径是过滤路径则直接返回
        if (isFilter(request)) {
            log.info("{}不需要过滤", request.getRequestURI());
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //获取token
            String token = request.getHeader("Token");
            if (token == null || token.isEmpty()) {
                throw new LocalRuntimeException("token 不能为空");
            }
            Map<String, Claim> userData = JwtUtil.valifyToken(token);

            if (userData == null) {
                throw new LocalRuntimeException("token 不合法");
            }

            Integer id = userData.get("id").asInt();
            String name = userData.get("name").asString();
            Integer age = userData.get("age").asInt();
            //拿到用户信息之后放到request中
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("age", age);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * 判断URL是否包含不过滤的路径
     *
     * @param request
     * @return
     */
    public boolean isFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        for (String noFilter : noFilters) {
            if (requestURI.contains(noFilter)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void destroy() {
        log.info("终止");
        javax.servlet.Filter.super.destroy();
    }
}
