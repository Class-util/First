/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 05
 * Time:17:58
 */

import utils.JSONServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class GetSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //PrintWriter writer = response.getWriter();

        String name = request.getParameter("username");
        String pwd = request.getParameter("password");

        int state = 0;
        String msg = "";

        if(name.equals("admin") && pwd.equals("123")){

            state = 1;

            HttpSession session = request.getSession();
            session.setAttribute("username",name);
            String id = session.getId();

            HashMap<String,Object> map = new HashMap<>();
            map.put("state",state);
            map.put("msg",msg);
            map.put("id",id);
            //writer.println(String.format("<h1>id:%s</h1>",id));
            JSONServletUtils.write(response,map);

        }else {
            msg = "用户名或密码错误！";
            HashMap<String,Object> map = new HashMap<>();
            map.put("state",state);
            map.put("msg",msg);
            JSONServletUtils.write(response,map);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
