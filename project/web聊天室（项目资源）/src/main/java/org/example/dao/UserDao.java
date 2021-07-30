package org.example.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.Util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 22
 * Time:11:30
 */
public class UserDao {

    public static User queryByName(String name) {
        //获取连接
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //返回数据
        User user = null;
        try{
            connection = Util.getConnection();
            String sql = "select * from user where name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(name);
                user.setPassword(resultSet.getString("password"));
                user.setIconPath(resultSet.getString("IconPath"));
                user.setSignature(resultSet.getString("Signature"));
                user.setNickName(resultSet.getString("NickName"));
                java.sql.Timestamp lastLogout = resultSet.getTimestamp("LastLogout");
                user.setLastLogout(new Date(lastLogout.getTime()));
            }
            return user;
        }catch (Exception e){
            throw new AppException("查询用户账号出错",e);
        }finally {
            Util.close(connection,preparedStatement,resultSet);
        }
    }

    public static int updateLastLogout(Integer userId) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = Util.getConnection();
            String sql = "update user set lastLogout=? where userId=?";
            ps = c.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, userId);
            return ps.executeUpdate();
        }catch (Exception e){
            throw new AppException("修改用户上次登录时间出错", e);
        }finally {
            Util.close(c, ps);
        }
    }
}
