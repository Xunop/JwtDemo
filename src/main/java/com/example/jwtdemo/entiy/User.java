package com.example.jwtdemo.entiy;

import lombok.*;

/**
 * @Author xun
 * @create 2022/4/29 11:52
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private Integer age = 12;
    private Integer id = 2;
    private String password;
    public User(String name, String password, Integer id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }
}
