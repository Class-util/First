package services;

import dao.ArtInfoDao;
import dao.UserInfoDao;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 07
 * Time:14:48
 */
@WebServlet("/addart")
public class AddArtServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succ = -1;
        String msg = "";
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("userinfo")  != null){
            UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
            //数据库
            if(title!= null && content != null && !title.equals("") && !content.equals("")) {
                ArtInfoDao artInfoDao = new ArtInfoDao();
                try {
                    succ = artInfoDao.add(title,content,userInfo.getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else {
                msg = "无效参数";
            }
        }else {
            msg = "当前为非登录状态！请先登录";
        }
        //将上一步结果返回给前端
        HashMap<String,Object> map = new HashMap<>();
        map.put("succ",succ);
        map.put("msg",msg);
        ResultJSONUtils.write(resp,map);
    }
}
