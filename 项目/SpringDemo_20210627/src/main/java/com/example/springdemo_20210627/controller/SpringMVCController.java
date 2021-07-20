package com.example.springdemo_20210627.controller;

import com.example.springdemo_20210627.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 06 27
 * Time:11:30
 */
//@Controller
//@ResponseBody
@RequestMapping("/mvc")
@RestController
@Slf4j
public class SpringMVCController {

    @Autowired
    private ObjectMapper mapper;

    @RequestMapping("/index8")
    @ResponseBody
    public User getIndex8() throws JsonProcessingException {
        User user = new User();
        user.setUsername("hhh");
        user.setPassword("123");
        String s = mapper.writeValueAsString(user);
        return user;
    }

    @RequestMapping("/index7")
    @ResponseBody
    public String getIndex7() throws JsonProcessingException {
        User user = new User();
        user.setUsername("hhh");
        user.setPassword("123");
        String s = mapper.writeValueAsString(user);
        return s;
    }

    @RequestMapping("/index6")
    @ResponseBody //表示返回类型为自定义类型，而非视图
    public String getIndex6(){
        return "/index.html";
    }

    @RequestMapping("/index")
    public String getIndex(){
        log.error("我是index");
        //return "redirect:/index.html";
        return "forward:/index.html";
    }

    @RequestMapping("/second")
    public String getSecond(){
        log.error("我是index");
        return "redirect:/index.html";
    }

    @RequestMapping("/index3")
    public String getIndex3(HttpServletResponse response,HttpServletRequest request){
        response.setStatus(301);
        response.setHeader("location","/index.html");
        return null;
    }

    @GetMapping("/index4")
    public String getIndex4(){
        log.error("index4");
        return "/index.html";
    }

    @PostMapping("/index5")
    public String getIndex5(){
        log.error("index5");
        return "/index.html";
    }
}
