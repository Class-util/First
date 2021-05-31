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
 * Time:14:24
 */
@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //定义状态信息
        int stats = -1;
        String msg = "";
        String username = req.getParameter("username");
        //非空效验
        if(username != null && !"".equals(username)){
            //数据库操作
            UserDao userDao = new UserDao();
            //进行删除的业务操作
            try {
                int ret = userDao.removeUser(username);
                if(ret == 0){
                    stats = ret;
                    msg = "删除失败";
                }else {
                    stats = ret;
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
        ResultJsonUtils.write(resp,map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
