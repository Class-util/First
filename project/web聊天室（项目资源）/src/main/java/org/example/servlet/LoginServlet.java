package org.example.servlet;

import org.example.dao.UserDao;
import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 21
 * Time:18:23
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    //检查登录状态接口
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        //获取当前的Session，并获取用户信息，如果去取不到，则返回ok：false
        HttpSession session = req.getSession(false);
        User user = new User();
        if(session != null){
            User get = (User) session.getAttribute("User");
            if(get != null){
                //已经登录，并获取到用户信息
                user = get;
                user.setOk(true);
                resp.getWriter().println(Util.serialize(user));
                return ;
            }
        }
        user.setOk(false);
        //3.返回响应数据:从响应对象获取输出流，打印输出到响应体body里
        resp.getWriter().println(Util.serialize(user));
    }

    //登录接口
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        //响应的数据，根据接口文档，user类中包含此信息
        User user = new User();
        try {
            //1.解析请求数据：根据接口文档，需要使用反序列化操作
            User input = Util.deserialize(req.getInputStream(),User.class);
            //2.业务处理:数据库查询，校验信息，设置session
            User query = UserDao.queryByName(input.getName());
            if(query == null){
                throw new AppException("用户不存在");
            }
            if(!query.getName().equals(input.getName())){
                throw new AppException("账号或密码错误");
            }
            //账号密码校验那成功
            HttpSession session = req.getSession();
            //反序列化的user对象中只存在用户名和密码，query是数据库查询出来的对象，包含有完成信息
            session.setAttribute("User",query);
            user = query;
            //构造操作成功的正常返回数据，ok：true，业务字段
            user.setOk(true);
        }catch (Exception e){
            //打印堆栈信息
            e.printStackTrace();
            //构造操作失败的错误信息，ok：false，reason：错误信息
            user.setOk(false);
            //自定义异常，自己抛，中文信息，可以给用户看
            if(e instanceof AppException){
                user.setReason(e.getMessage());
            }else {
                //不是自定义异常
                user.setReason("未知的错误，请联系管理员");
            }
        }
        //3.返回响应数据:从响应对象获取输出流，打印输出到响应体body里
        resp.getWriter().println(Util.serialize(user));
    }
}
