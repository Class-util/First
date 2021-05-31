package services;

import dao.UserDao;
import models.User;
import util.ResultJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 28
 * Time:15:31
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //定义状态信息
        int stats = -1;
        String msg = "";
        String username = req.getParameter("username");
        int flog = Integer.parseInt(req.getParameter("flog"));

        //查询操作
        if(flog == 1){
            User user = null;
            //非空效验
            if(username != null && !"".equals(username)){
                //数据库操作
                UserDao userDao = new UserDao();
                //进行查询的业务操作
                try {
                    user= userDao.getUser(username);
                    if(user != null){
                        stats = 1;
                    }else {
                        stats = 0;
                        msg = "查询失败";
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else {
                stats = 0;
                msg = "用户名不能为空";
            }
            HashMap<String,Object> map = new HashMap<>();
            map.put("stats",stats);
            map.put("msg",msg);
            map.put("username",user.getName());
            map.put("password",user.getPassword());
            ResultJsonUtils.write(resp,map);
        }
        if(flog == 2){
            //更新操作
            //从前端获取参数
            String username1 = req.getParameter("username1");
            String password = req.getParameter("password");
            UserDao userDao = new UserDao();
            try {
                int ret = userDao.updateUser(username,username1,password);
                if(ret == 0){
                    stats = ret;
                    msg = "用户信息修改失败!";
                }else {
                    stats = ret;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            HashMap<String,Object> map = new HashMap<>();
            map.put("stats",stats);
            map.put("msg",msg);
            ResultJsonUtils.write(resp,map);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
