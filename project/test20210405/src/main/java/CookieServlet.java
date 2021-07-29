/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 05
 * Time:12:36
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        //创建cookie
        Cookie cookie = new Cookie("admin","123");
        //设置cookie最大存活时间
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        PrintWriter writer = response.getWriter();
        writer.println("<h1>cookie添加成功</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
