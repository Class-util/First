package services; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 04
 * Time:11:48
 */

import dao.UserInfoDao;
import utils.DBUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        //定义返回给前端的参数
        int succ = 0;
        String msg = "";
        //从前端获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //非空效验
        //if(username != null && password != null && !username.equals("") && !password.equals("")){
            //如果都不为空，则进行数据库连接，进行数据添加
            UserInfoDao userInfoDao = new UserInfoDao();
            try {
                succ = userInfoDao.add(username,password);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        //}
        //返回结果
        PrintWriter writer = response.getWriter();
        // {"succ":1,"msg":"msg"}
        writer.println(String.format("{\"succ\":%d,\"msg\":\"%s\"}",
                succ, msg));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
