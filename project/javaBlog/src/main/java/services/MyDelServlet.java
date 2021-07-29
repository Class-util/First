package services;

import dao.ArtInfoDao;
import utils.ResultJSONUtils;

import javax.jws.WebService;
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
 * Date:2021 04 07
 * Time:12:50
 */
@WebServlet("/mydel")
public class MyDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        //从前端获取参数
        int id = Integer.parseInt(req.getParameter("id"));
        //调用数据库
        ArtInfoDao artInfoDao = new ArtInfoDao();
        try {
            succ = artInfoDao.del(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //将上一步结果返回给前端
        HashMap<String,Object> map = new HashMap<>();
        map.put("succ",succ);
        map.put("msg",msg);
        ResultJSONUtils.write(resp,map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
