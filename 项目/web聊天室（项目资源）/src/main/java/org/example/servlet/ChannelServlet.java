package org.example.servlet;

import org.example.dao.ChannelDao;
import org.example.exception.AppException;
import org.example.model.Channel;
import org.example.model.Response;
import org.example.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 22
 * Time:20:01
 */
@WebServlet("/channel")
public class ChannelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Response response = new Response();
        try {
            //查询所有频道列表返回
            List<Channel> list = ChannelDao.query();
            response.setData(list);
            response.setOk(true);
        }catch (Exception e){
            e.printStackTrace();

            if(e instanceof AppException){
                response.setReason(e.getMessage());
            }else {
                response.setReason("发生未知的错误，请联系管理员");
            }
        }
        resp.getWriter().println(Util.serialize(response));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
