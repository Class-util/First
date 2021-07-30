package com.example.springdemo_20210627.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:自定义拦截器
 * User:吴博
 * Date:2021 07 17
 * Time:22:36
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    //自定义拦截器，true表示可以访问后端接口，false无权访问后端接口
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断Session是否有值
        HttpSession session = request.getSession(false);
        if(session != null
                && session.getAttribute("user") != null){
            //说明已经登录
            return true;
        }
        return false;
    }
}
