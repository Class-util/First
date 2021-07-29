package org.example.dao;

import org.example.exception.AppException;
import org.example.model.Message;
import org.example.model.User;
import org.example.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 22
 * Time:11:30
 */
public class MessageDao {
    public static int insert(Message msg) {
        Connection connection = null;
        PreparedStatement preparedStatement =  null;
        try {
            connection = Util.getConnection();
            String sql = "insert into message values(null,?,?,?,?)";
            preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setInt(1,msg.getUserId());
            preparedStatement.setInt(2,msg.getChannelId());
            preparedStatement.setString(3,msg.getContent());
            preparedStatement.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
            return preparedStatement.executeUpdate();
        }catch (Exception e){
            throw new AppException("消息保存出错");
        }finally {
            Util.close(connection,preparedStatement);
        }
    }

    public static List<Message> queryByLastLogout(Integer userId) {
        //获取连接
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //返回数据
        List<Message> list = new ArrayList<>();
        try{
            connection = Util.getConnection();
            String sql = "select m.*,u.nickName from message m join user u on u.userId = m.userId where m.sendTime>" +
                    "(select " +
                    "lastLogout from user " +
                    "where userId = ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Message message = new Message();
                //获取结果集字段，设置对象属性
                message.setUserId(userId);
                message.setNickName(resultSet.getString("nickName"));
                message.setContent(resultSet.getString("content"));
                message.setChannelId(resultSet.getInt("channelId"));
                list.add(message);
            }
            return list;
        }catch (Exception e){
            throw new AppException("查询用户["+ userId +"]的历史消息出错",e);
        }finally {
            Util.close(connection,preparedStatement,resultSet);
        }
    }
}
