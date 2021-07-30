package com.example.usermananger.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 22
 * Time:17:48
 */
@Data
public class UserInfo {
    private int id;
    private String name;
    private String username;
    private String password;
    private String sex;
    private int age;
    private String qq;
    private String email;
    private int isadmin;
    private String address;

}
