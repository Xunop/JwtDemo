package com.example.jwtdemo;

import com.example.jwtdemo.entiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JwtDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(JwtDemoApplication.class, args);
    }

}
