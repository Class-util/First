package com.example.springdemo_20210627.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:对所有的接口返回值做封装
 * User:吴博
 * Date:2021 07 18
 * Time:11:03
 */
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    //注入对象
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    //重写的方法是否生效
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    //处理异常
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("status",0);
        map.put("data",o);
        map.put("msg","");
        //判断o是否是String类型
/*        if(o instanceof String){
            //设置当前返回给前端json类型的字符串
            serverHttpResponse.getHeaders().setContentType(mediaType.APPLICATION_JSON);
            return objectMapper.writeValueAsString(map);
        }*/
        return map;
    }
}
