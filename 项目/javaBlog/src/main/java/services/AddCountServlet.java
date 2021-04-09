package services;

import dao.ArtInfoDao;
import utils.ResultJSONUtils;

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
 * Date:2021 04 09
 * Time:16:49
 */
@WebServlet("/addcount")
public class AddCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        int id = Integer.parseInt(req.getParameter("id"));
        if(id > 0){
            ArtInfoDao artInfoDao = new ArtInfoDao();
            try {
                succ = artInfoDao.upRcount(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            msg = "参数无效";
        }
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
