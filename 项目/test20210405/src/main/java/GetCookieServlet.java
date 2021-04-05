/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 05
 * Time:12:15
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class GetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        PrintWriter writer = response.getWriter();
        for (Cookie cookie : cookies) {
            writer.println(String.format("<h1>name = %s ; pwd = %s</h1>",cookie.getName(),cookie.getValue()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
