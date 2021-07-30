package com.example.usermananger.tools;

import com.example.usermananger.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 24
 * Time:10:30
 */
public class SessionUtil {

    public static UserInfo getUserBySession(HttpServletRequest request){
        UserInfo userinfo = null;
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(AppFinal.USERINFO_SESSIONKEY) != null){
            userinfo = (UserInfo) session.getAttribute(AppFinal.USERINFO_SESSIONKEY);
        }
        return userinfo;
    }

}
