package com.example.springdemo_20210627.controller;

import com.example.springdemo_20210627.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 06 27
 * Time:15:30
 */
@RequestMapping("/met")
@RestController
public class ControllerDemo {

    @GetMapping("/owner/{pid}/animal/{aid}")
    public String func1(@PathVariable String pid,@PathVariable("aid") String animals){
        return "pid:"+pid+",aid:"+animals;
    }

    @RequestMapping("/getname")
    public String func2(String name){
        return "姓名：" + name;
    }

    @RequestMapping("/login")
    public String func3(@RequestBody(required = false) String name){
        return "姓名：" + name;
    }

    @RequestMapping("/login1")
    public String func4(@RequestParam(required = false) String name){
        return "name:" + name;
    }

    @RequestMapping("/reg")
    public String reg(@RequestParam String username,
                      @RequestParam String password,
                      @RequestParam String img){
        return "username:" + username + ",password:" + password + ",img:" + img;
    }

    @RequestMapping("/reg1")
    public String reg1(@RequestBody User user){
        return "username:" + user.getUsername() + ",password:" + user.getPassword();
    }
}
