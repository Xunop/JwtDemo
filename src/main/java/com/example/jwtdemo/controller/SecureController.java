package com.example.jwtdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author xun
 * @create 2022/4/29 15:10
 */
@Slf4j
@RestController
public class SecureController {

    /**
     * 查询用户信息，登录后才可访问
     */
    @RequestMapping("/secure/getUserInfo")
    public String getUserInfo(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id");
//        String name = request.getAttribute("name").toString();
//        String age = request.getAttribute("age").toString();
        return "当前用户信息id=" + id;
    }
}
