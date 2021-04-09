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

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 07
 * Time:13:49
 */
@WebServlet("/init")
public class InitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        ArticleInfoVO artInfovo = null;
        //从前端获取参数
        int id = Integer.parseInt(req.getParameter("id"));
        //操作数据库
        if(id > 0){
            ArtInfoDao artInfoDao  = new ArtInfoDao();
            succ = 1;
            try {
                artInfovo = artInfoDao.getArtById(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            msg = "无效参数";
        }
        //将上一步结果返回给前端
        HashMap<String,Object> map = new HashMap<>();
        map.put("succ",succ);
        map.put("msg",msg);
        map.put("art",artInfovo);
        ResultJSONUtils.write(resp,map);
    }
}
