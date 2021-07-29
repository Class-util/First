package dao;

import models.User;
import util.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:数据库业务
 * User:吴博
 * Date:2021 05 11
 * Time:15:16
 */
public class UserDao {

    //查询用户
    public User getUser(String username, String password) throws SQLException {
        Connection connection = DButils.getConnection();
        String sql = "select * from userMsg where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        User user = new User();
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            user.setName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }
        DButils.close(connection,preparedStatement,resultSet);
        return user;
    }
    //通过用户名查询用户信息
    public User getUser(String username) throws SQLException {
        Connection connection = DButils.getConnection();
        String sql = "select * from userMsg where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        User user = new User();
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            user.setName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }
        DButils.close(connection,preparedStatement,resultSet);
        return user;
    }

    //注册
    public int add(String username, String password) throws SQLException {
        int ret = 0;
        Connection connection = DButils.getConnection();
        String sql = "insert into userMsg(username,password) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ret = preparedStatement.executeUpdate();
        DButils.close(connection,preparedStatement,null);
        return ret;
    }

    //用户数据删除操作
    public int removeUser(String username) throws SQLException {
        int ret = 0;
        Connection connection = DButils.getConnection();
        String sql = " delete from usermsg where username = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ret = preparedStatement.executeUpdate();
        return ret;
    }
    //修改用户信息
    public int updateUser(String username, String username1, String password) throws SQLException {
        int ret = 0;
        Connection connection = DButils.getConnection();
        String sql = "update usermsg set username = ?,password = ? where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username1);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,username);
        ret = preparedStatement.executeUpdate();
        return ret;
    }
}
