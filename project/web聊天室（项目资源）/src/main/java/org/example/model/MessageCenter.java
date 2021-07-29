package org.example.model;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:保存websocket需要的信息：所有客户端Session
 * User:吴博
 * Date:2021 05 22
 * Time:21:56
 */
public class MessageCenter {
    /**
     * 支持线程安全的map结构，并且满足高并发（读写，读读并发，写写互斥）
     * */
    private static final ConcurrentHashMap<Integer, Session> client = new ConcurrentHashMap<>();

    /**
     * websocket建立连接时，添加用户id和客户端session，保存起来
     * */

    public static void addOnlineUser(Integer userId,Session session){
         client.put(userId,session);
     }
     /**
      * 关闭websocket连接，和出错了，删除客户端session
      * */
     public static void delOnlineUser(Integer userId){
         client.remove(userId);
     }
     /**
      * 接收到某用户的消息，转发所有客户端
      * */
     public static void sendMessage(String message){
         Enumeration<Session> elements = client.elements();
         try {
             while (elements.hasMoreElements()){
                 Session session = elements.nextElement();
                 session.getBasicRemote().sendText(message);
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
