package services; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 11
 * Time:14:51
 */

import dao.UserDao;
import models.User;
import util.ResultJsonUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //定义状态信息
        int stats = -1;
        String msg = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //非空效验
        if(username != null && password != null && !"".equals(username) && !"".equals(password)){
            //数据库操作
            UserDao userDao = new UserDao();
            try {
                User user = userDao.getUser(username, password);
                if(username.equals(user.getName()) && password.equals(user.getPassword())){
                    stats = 1;
                }else {
                    stats = 0;
                    msg = "查无此人！";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            msg = "用户名或密码错误！";
            stats = 0;
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("stats",stats);
        map.put("msg",msg);
        ResultJsonUtils.write(response,map);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
