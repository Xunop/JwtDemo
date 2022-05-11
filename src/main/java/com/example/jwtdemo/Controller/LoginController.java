package com.example.jwtdemo.Controller;

import com.example.jwtdemo.entiy.User;
import com.example.jwtdemo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xun
 * @create 2022/4/29 14:57
 */
@RestController
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    //模拟数据库
    static Map<Integer, User> userMap = new HashMap<>();
    static {
        User user1 = new User("xxx",1,19,"123456");
        userMap.put(1, user1);
        User user2 = new User("yyy",1,1,"123456");
        userMap.put(2, user2);
    }

    /**
     * 模拟用户登录
     */

    @GetMapping ("/login")
    public String login(User user) {
            System.out.println("test");
                String token = JwtUtil.createToken(user);
                logger.info("登录成功！生成token！");
                return token;
    }
}
