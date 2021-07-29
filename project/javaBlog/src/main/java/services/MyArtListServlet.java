package services; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 04
 * Time:17:04
 */

import dao.ArtInfoDao;
import models.ArtInfo;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyArtListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArtInfo> list = new ArrayList<>();
        int succ = -1;
        String msg = "";
        HttpSession session = request.getSession(false);
        if(session == null){
            msg = "用户未登录!";
        }else {
            //获取session
            UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
            //得到uid
            int uid = userInfo.getId();
            ArtInfoDao artInfoDao = new ArtInfoDao();
            try {
                list = artInfoDao.getAtrListByUID(uid);
                succ = 1;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            HashMap<String,Object> map = new HashMap<>();
            map.put("succ",succ);
            map.put("msg",msg);
            map.put("list",list);
            ResultJSONUtils.write(response,map);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
