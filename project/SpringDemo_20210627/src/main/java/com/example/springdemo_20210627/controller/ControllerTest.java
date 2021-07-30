package com.example.springdemo_20210627.controller;

import com.example.springdemo_20210627.config.AppFinal;
import com.example.springdemo_20210627.mapper.UserMapper;
import com.example.springdemo_20210627.model.ArticleInfo;
import com.example.springdemo_20210627.model.User;
import com.example.springdemo_20210627.tools.HtmlText;
import com.fasterxml.jackson.databind.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 06 27
 * Time:16:49
 */
@Slf4j
@Controller
@RequestMapping("/log")
public class ControllerTest {

    @Autowired
    //@Resource
    private UserMapper userMapper;

    //读取配置文件中的信息
    @Value("${myimagpath}")
    private String imgpath;


    //文章查询功能
    @RequestMapping("/getalist")
    @ResponseBody
    public Object getFullUser(int uid){
        User user = userMapper.getFullUser(uid);
        //对文章正文进行截取
        List<ArticleInfo> alist = user.getAlist();
        for(ArticleInfo item : alist){
            // 去除html标签
            String desc = HtmlText.Html2Text(item.getContent());
            // 截取字符串
            if (desc.length() > 64) {
                desc = desc.substring(0, 64) + "...";
            }
            item.setContent(desc);
        }
        user.setAlist(alist);
        return user;
    }

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Object login(User user, HttpServletRequest request){
        User user1 = userMapper.getUserByNameAndPassword(user.getUsername(),user.getPassword());
        if(user1 == null){
            user1 = user;
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("user",user1);
        }
        return user1;

        /*int count = 10 / 0;
        HashMap<String,Object> map = new HashMap<>();
        int status = -1;
        String msg = "";
        String data = "";
        if(user != null && "root".equals(user.getUsername()) && "root".equals(user.getPassword())){
            msg = "!";
            data = "登录成功";
            status = 0;
            map.put("user",user);
            map.put("msg",msg);
            map.put("data",data);
            map.put("status",status);
            //添加Session
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        }else {
            msg = "用户名或密码错误";
            data = "登陆失败";
            map.put("user",null);
            map.put("msg",msg);
            map.put("data",data);
            map.put("status",status);
        }
        return map;*/
    }

    //注册路由
    @RequestMapping("/login2")
    //当前方法返回值为数据而非视图
    @ResponseBody
    public Object login2(@RequestParam String username, HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        int status = -1;
        String msg = "未知错误";
        String data = "登陆失败";
        if("root".equals(username)){
            HttpSession session = request.getSession();
            session.setAttribute("name",username);
            status = 0;
            msg = "";
            data = "登录成功";
        }else {
            status = 1;
            msg = "用户名错误";
            data = "登陆失败";
        }
        map.put("status",status);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }

    //注册路由
    @RequestMapping("/login3")
    //当前方法返回值为数据而非视图
    @ResponseBody
    //requestParam可以实现非空效验（默认此参数必填，否则报400错误）
    //可以实现将前端的参数指定映射到某个参数上
    public Object login3(@RequestParam(name = "name") String username, @RequestParam String password,
                         HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        int status = -1;
        String msg = "未知错误";
        String data = "登陆失败";
        map.put("status",status);
        map.put("msg",msg);
        map.put("data",username);
        return map;
    }

    //上传文件
    @RequestMapping("/reg")
    @ResponseBody
    public Object regin(String username, @RequestPart MultipartFile file) throws IOException {

        //动态获取当前项目的运行路径
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        path += "/upload/";
        log.error("path:" + path);

        //文件类型
        String fileType = file.getOriginalFilename();
        fileType = fileType.substring(fileType.lastIndexOf("."));
        //文件名（UUID全局唯一ID）+文件的原始类型
        String fileName = UUID.randomUUID().toString() + fileType;
        log.error("路径:" + path+fileName);
        //将文件保存到服务器（也就是当前的本地环境）
        file.transferTo(new File(path+fileName));
        return null;
    }

    @RequestMapping("/reg1")
    public String reg(String username,
                      String password,
                      String password2,
                      @RequestPart MultipartFile file) throws IOException {
/*        HashMap<String,Object> map = new HashMap<>();
        //定义状态
        int status = -1;
        String data = "注册成功！";
        String msg = "";
        //注解帮我们判断了非空，我们只需要比较两次密码是否相同
        if(password.equals(password1)){
            status = 0;
            map.put("username",username);
            map.put("password",password);
            map.put("data",data);
            map.put("status",status);*/

            //上传头像
            //1.获取当前项目的动态路径，获取的是target目录下的路径
            //String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
            String path = imgpath;
            path += AppFinal.IMAG_PATH;
            //获取文件类型
            String fileType = file.getOriginalFilename();
            fileType = fileType.substring(fileType.lastIndexOf("."));
            //设置文件名
            String fileName = UUID.randomUUID().toString() + fileType;
            //保存文件
            file.transferTo(new File(path+fileName));

            //将用户信息存入数据库中
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPhoto(AppFinal.IMAG_PATH+fileName);
            int res = userMapper.addUser(user);
            if(res > 0){
                //操作成功
                return "redirect:/reg_success.html";
            }else {
                //操作失败
                return "redirect:/reg_err.html";
            }
/*        }*/
    }

    @RequestMapping("/reg2")

    public String reg1(String username,
                       String password,
                       String password2,
                        @RequestPart MultipartFile file) throws IOException {
        // todo:非空效验
        // 1.动态获取当前项目的路径
//        String path = ClassUtils.getDefaultClassLoader().
//                getResource("static").getPath();
        //String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String path = imgpath;
        path += AppFinal.IMAG_PATH;
        // 2.文件名（全局唯一id【UUID】）+文件的原始类型
        String fileType = file.getOriginalFilename(); // img.png
        fileType = fileType.substring(fileType.lastIndexOf("."));
        // 文件名
        String fileName = UUID.randomUUID().toString() + fileType;
        // 将文件保存到服务器
        file.transferTo(new File(path + fileName));

        // 将用户信息存储到服务器
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoto(AppFinal.IMAG_PATH + fileName); //设置头像地址
        int result = userMapper.addUser(user);
        if (result > 0) {
            // 操作成功
            return "redirect:/reg_success.html";
        } else {
            return "redirect:/reg_err.html";
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object getCookies(@CookieValue(value = "mysessionId",required = false) String cookieid){
        return cookieid;
    }

    @RequestMapping("/method1")
    @ResponseBody
    public Object method1(){
        Object data = "method1";
        return data;
    }

}
