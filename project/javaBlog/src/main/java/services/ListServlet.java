package services;

import dao.ArtInfoDao;
import models.ArtInfo;
import models.vo.ArticleInfoVO;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 09
 * Time:15:14
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        List<ArticleInfoVO> list = null;

        int page =  Integer.parseInt(req.getParameter("page"));
        int psize =  Integer.parseInt(req.getParameter("psize"));

        ArtInfoDao artInfoDao = new ArtInfoDao();
        try {
            list = artInfoDao.getLietByPage(page,psize);
            succ = 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("succ",succ);
        map.put("msg",msg);
        map.put("list",list);
        ResultJSONUtils.write(resp,map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
