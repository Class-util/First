package com.example.springdemo_20210627.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 06 27
 * Time:10:15
 */
@RequestMapping("/user")
@Controller
@ResponseBody
@Slf4j
public class UserController {
    //创建一个当前类的日志对象
    //private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/index")
    public String getIndex(String name){
        if(name == null || "".equals(name)){
            log.error("日志信息：error级别");
        }
        log.warn("日志信息：warn级别");
        log.info("日志信息：info级别");
        log.trace("日志信息：trace级别");
        log.debug("日志信息：debug级别");
        return "早上好";
    }
}
