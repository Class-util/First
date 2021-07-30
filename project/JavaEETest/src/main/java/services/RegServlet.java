package services;

import dao.UserDao;
import util.ResultJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:注册
 * User:吴博
 * Date:2021 05 11
 * Time:16:58
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            //定义返回给前端的参数
            int stats = 0;
            String msg = "";
            //从前端获取参数
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //非空效验
            //if(username != null && password != null && !username.equals("") && !password.equals("")){
            //如果都不为空，则进行数据库连接，进行数据添加
            UserDao userDao = new UserDao();
            try {
                stats = userDao.add(username,password);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            //}
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
