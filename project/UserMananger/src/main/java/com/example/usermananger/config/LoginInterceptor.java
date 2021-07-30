package com.example.usermananger.config;

import com.example.usermananger.tools.AppFinal;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:自定义拦截器
 * User:吴博
 * Date:2021 07 29
 * Time:10:40
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null &&session.getAttribute(AppFinal.USERINFO_SESSIONKEY) != null){
            return true;
        }
        return false;
    }
}
