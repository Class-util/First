package org.example.servlet;


import org.example.model.MessageCenter;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

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
    public void onOpen(@PathParam("userId")Integer userId, Session session){
        MessageCenter.addOnlineUser(userId,session);
        System.out.println("建立连接:" + userId);
    }
    @OnMessage
    public void onMessage(Session session,String message){
        MessageCenter.sendMessage(message);
    }
    @OnClose
    public void onClose(){

    }
    @OnError
    public void onError(Throwable error){
        System.out.println("出错了");
        error.printStackTrace();
    }
}
