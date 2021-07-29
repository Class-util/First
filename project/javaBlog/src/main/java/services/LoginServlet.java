package services; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 04
 * Time:15:54
 */

import dao.UserInfoDao;
import models.UserInfo;
import utils.DBUtils;
import utils.ResultJSONUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //非空效验
        if(username != null && password != null && !username.equals("") && !password.equals("")){
            //不为空进行数据库查询
            UserInfoDao userInfoDao = new UserInfoDao();
            try {
                UserInfo user = userInfoDao.getUser(username, password);
                if(user.getId() > 0){
                    succ = 1;
                    //设置session
                    HttpSession session = request.getSession();
                    session.setAttribute("userinfo",user);
                }else {
                    succ = 0;
                    msg = "用户名或密码错误";
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            msg = "参数不完整";
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("succ",succ);
        map.put("msg",msg);
        ResultJSONUtils.write(response,map);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
