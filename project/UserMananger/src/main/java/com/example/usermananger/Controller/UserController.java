package com.example.usermananger.Controller;

import com.example.usermananger.mapper.UserMapper;
import com.example.usermananger.model.UserInfo;
import com.example.usermananger.tools.AppFinal;
import com.example.usermananger.tools.ResponseBody;
import com.example.usermananger.tools.SessionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 22
 * Time:17:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    //登录
    @RequestMapping("/login")
    public ResponseBody login(@RequestParam String username,
                              @RequestParam String password,
                              HttpServletRequest request){
        UserInfo userInfo = userMapper.login(username, password);
        int status = -1;
        String message = "用户名或密码错误";
        if(userInfo != null && userInfo.getId() > 0){
            status = 0;
            message = "";
            HttpSession session = request.getSession();
            session.setAttribute(AppFinal.USERINFO_SESSIONKEY,userInfo);
        }
        return new ResponseBody(status,message,userInfo);
    }

    //检验是否是管理员
    @RequestMapping("/checkadmin")
    public ResponseBody<Integer> checkAdmin(HttpServletRequest request){
        int data = 0;
        HttpSession session = request.getSession(false);
        UserInfo userinfo = null;
        if(session != null && session.getAttribute(AppFinal.USERINFO_SESSIONKEY) != null){
            userinfo = (UserInfo) session.getAttribute(AppFinal.USERINFO_SESSIONKEY);
            data = userinfo.getIsadmin();
        }
        return new ResponseBody<>(0,"",data);
    }

    //添加方法
    @RequestMapping("/add")
    public ResponseBody<Integer> add(UserInfo userInfo,HttpServletRequest request){
        int status = 0;
        int data = 0;
        String message = "";
        //安全效验
        UserInfo userBySession = SessionUtil.getUserBySession(request);
        //没登录
        if(userBySession == null){
            status = -1;
            message = "用户未登录";
            //登录了验证权限
        }else if(userInfo.getIsadmin() == 1){
            //权限查询为0，权限不够添加超级管理员
             if(userBySession.getIsadmin() == 0){
                 status = -2;
                 message = "权限不够";
                 //权限够，直接添加
            }else if(userBySession.getIsadmin() == 1){
                 status = 0;
                 data = userMapper.add(userInfo);
             }
             //登录了，直接添加普通管理员
        }else if(userBySession.getIsadmin() == 0){
            data = userMapper.add(userInfo);
        }
        return new ResponseBody<>(status,message,data);
    }

    //查询方法
    @RequestMapping("/getuser")
    public ResponseBody<UserInfo> getUser(@RequestParam int uid){
        int status = -1;
        String message = "未知错误";
        UserInfo userInfo = userMapper.getUser(uid);
        if(userInfo != null){
            status = 0;
        }
        return new ResponseBody<>(status,message,userInfo);
    }

    //修改
    @RequestMapping("/update")
    public ResponseBody<Integer> update(UserInfo userInfo){
        int data = 0;
        int status = 0;
        String message = "";
        data = userMapper.update(userInfo);
        return new ResponseBody<>(status,message,data);
    }

    @RequestMapping("/list")
    public ResponseBody<HashMap<String,Object>> getList(String name,
                                                        String address,
                                                        String email,
                                                        int cpage,
                                                        int psize,
                                                        HttpServletRequest request){
        UserInfo userBySession = SessionUtil.getUserBySession(request);
        if(userBySession == null){
            return new ResponseBody<>(-1,"用户未登录",null);
        }
        //权限处理，权限不为1，那就赋值为0，只去查权限为0的同级
        //权限为1，则不需要赋值，直接查询全部，用的是动态sql
        Integer isadmin = null;
        if(userBySession.getIsadmin() == 0){
            isadmin = 0;
        }
        name = name.equals("")?null:name;
        address = address.equals("")?null:address;
        email = email.equals("")?null:email;
        //跳过查询的条数
        int skipCount = (cpage - 1) * psize;
        //查询一页信息
        List<UserInfo> list = userMapper.getListByPage(name,address,email,skipCount,psize,isadmin);
        //查询满足条件的数据条数
        int tcount = userMapper.getCount(name,address,email,isadmin);
        //计算总页数
        int tpage = (int)Math.ceil(tcount / (psize * 1.0));
        HashMap<String,Object> data = new HashMap<>();
        data.put("list",list);
        data.put("tcount",tcount);
        data.put("tpage",tpage);
        //传回userBySession对象，用来给前端判断是否显示删除按钮
        data.put("user",userBySession);
        return new ResponseBody<>(0,"",data);
    }

    //删除
    @RequestMapping("/del")
    public ResponseBody<Integer> del(@RequestParam int id,HttpServletRequest request){
        //权限效验
        UserInfo userBySession = SessionUtil.getUserBySession(request);
        if(userBySession == null){
            return new ResponseBody<>(-1,"没登录",0);
        }
        //判断删除的是否是自己
        if(userBySession.getId() == id){
            return new ResponseBody<>(-2,"不能删除自己",0);
        }
        Integer isadmin = null;
        if(userBySession.getIsadmin() == 0){
            isadmin = 0;
        }
        int res = userMapper.del(id,isadmin);
        return new ResponseBody<>(0,"",res);
    }

    //多条删除
    @RequestMapping("/dels")
    public ResponseBody<Integer> dels(String ids){
        int res = 0;
        res = userMapper.dels(ids.split(","));
        return new ResponseBody<>(0,"",res);
    }
}
