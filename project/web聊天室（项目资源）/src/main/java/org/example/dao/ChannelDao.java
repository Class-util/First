package org.example.dao;

import org.example.exception.AppException;
import org.example.model.Channel;
import org.example.model.User;
import org.example.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ChannelDao {
    public static List<Channel> query() {
        //获取连接
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //返回数据
        List<Channel> list = new ArrayList<>();
        try{
            connection = Util.getConnection();
            String sql = "select * from channel";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Channel channel = new Channel();
                //设置属性
                channel.setChannelId(resultSet.getInt("channelId"));
                channel.setChannelName(resultSet.getString("channelName"));
                list.add(channel);
            }
            return list;
        }catch (Exception e){
            throw new AppException("查询频道信息出错",e);
        }finally {
            Util.close(connection,preparedStatement,resultSet);
        }
    }
}
