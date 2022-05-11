package com.example.jwtdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwtdemo.entiy.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author xun
 * @create 2022/4/29 11:41
 */
@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    //定义token返回头部
    private static String header;

    //token前缀
    private static String tokenPrefix;

    //签名密钥
    private static String secret = "my_secret";

    //有效期
    private static long expireTime;

    //存进客户端的token的key名
    public static final String USER_LOGIN_TOKEN = "USER_LOGIN_TOKEN";

    public static void setExpireTime(long expireTime) {
        JwtUtil.expireTime = expireTime * 1000L * 60;
    }

    public static void setTokenPrefix(String tokenPrefix) {
        JwtUtil.tokenPrefix = tokenPrefix;
    }

    public static void setHeader(String header) {
        JwtUtil.header = header;
    }

    public static long getExpireTime() {
        return expireTime;
    }

    public static void setSecret(String secret) {
        JwtUtil.secret = secret;
    }

    public static String getSecret() {
        return secret;
    }

    public static String getTokenPrefix() {
        return tokenPrefix;
    }

    public static String getHeader() {
        return header;
    }

    /**
     * 创建TOKEN
     * @param user
     * @return
     */
    public static String createToken (User user){
        return JWT.create()
                .withClaim("id",user.getId())
                //user的name
                .withClaim("name", user.getName())
                //user的age
                .withClaim("age",user.getAge())
                //超时设置，设置过期的日期
                //secret加密
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static Map<String, Claim> valifyToken(String token){
        try{
            return JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token)
                    .getClaims();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
