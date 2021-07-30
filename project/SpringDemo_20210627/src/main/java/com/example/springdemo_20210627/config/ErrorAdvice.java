package com.example.springdemo_20210627.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:异常处理
 * User:吴博
 * Date:2021 07 18
 * Time:10:16
 */
//controller的增强类
@ControllerAdvice
public class ErrorAdvice {
    //异常管理器
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object err(Exception e){
        HashMap<String,Object> map = new HashMap<>();
        map.put("status","-1");
        map.put("data","");
        map.put("msg",e.getMessage());
        return map;
    }
}
