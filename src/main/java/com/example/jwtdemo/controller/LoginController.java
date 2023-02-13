package com.example.jwtdemo.controller;

import com.example.jwtdemo.entiy.User;
import com.example.jwtdemo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xun
 * @create 2022/4/29 14:57
 */
@Slf4j
@RestController
public class LoginController {

    //模拟数据库
    static Map<Integer, User> userMap = new HashMap<>();

    static {
        User user1 = new User("xxx", 1, 2, "123456");
        userMap.put(user1.getId(), user1);
        User user2 = new User("yyy", 1, 1, "1234567");
        userMap.put(user2.getId(), user2);
    }

    /**
     * 模拟用户登录
     */
    @PostMapping("/login")
    public String login(@RequestParam(defaultValue = "") String name,
                        @RequestParam(defaultValue = "") String password, Integer id) {
        if (!userMap.containsKey(id)) {
            return "不存在此用户";
        }
        User curUser = userMap.get(id);
        if (curUser.getName().equals(name) && curUser.getPassword().equals(password)) {
            User user = new User(name, password, id);
            String token = JwtUtil.createToken(user);
            log.info("登录成功！生成token！");
            return token;
        } else {
            return "密码错误";
        }
    }
}
