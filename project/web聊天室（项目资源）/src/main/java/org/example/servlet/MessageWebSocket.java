package org.example.servlet;


import org.example.dao.MessageDao;
import org.example.dao.UserDao;
import org.example.model.Message;
import org.example.model.MessageCenter;
import org.example.util.Util;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 22
 * Time:20:45
 */
@ServerEndpoint("/message/{userId}")
public class MessageWebSocket {
    @OnOpen
    public void onOpen(@PathParam("userId")Integer userId, Session session) throws IOException {
        MessageCenter.addOnlineUser(userId,session);
        List<Message> list = MessageDao.queryByLastLogout(userId);
        for(Message m : list){
            session.getBasicRemote().sendText(Util.serialize(m));
        }
        System.out.println("建立连接:" + userId);
    }
    @OnMessage
    public void onMessage(Session session,String message){
        MessageCenter.sendMessage(message);
        //反序列化json字符串为message对象
        Message msg = Util.deserialize(message,Message.class);
        //message设置接收消息的时间
        //msg.setSendTime(new Date());
        //插入数据库
        int n = MessageDao.insert(msg);
        System.out.printf("接收到消息：%s\n",message);
    }
    @OnClose
    public void onClose(@PathParam("userId") Integer userId){
        MessageCenter.delOnlineUser(userId);
        int n = UserDao.updateLastLogout(userId);
        System.out.println("关闭连接");
    }
    @OnError
    public void onError(@PathParam("userId") Integer userId,Throwable error){
        System.out.println("出错了");
        MessageCenter.delOnlineUser(userId);
        error.printStackTrace();
    }
}
