package dao;

import models.UserInfo;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 04
 * Time:11:53
 */
public class UserInfoDao {
    //对info表进行操作
    //用户注册信息添加到数据库
    public int add (String username,String password) throws SQLException {
        int ret = 0;
        Connection connection = DBUtils.getConnection();
        String sql = "insert into userinfo(username,password) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ret = preparedStatement.executeUpdate();
        DBUtils.close(connection,preparedStatement,null);
        return ret;
    }

    //查询用户
    public UserInfo getUser(String username, String password) throws SQLException {
        Connection connection = DBUtils.getConnection();
        UserInfo userInfo = new UserInfo();
        String sql = "select * from userinfo where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            userInfo.setId(resultSet.getInt("id"));
            userInfo.setUsername(resultSet.getString("username"));
            userInfo.setPassword(resultSet.getString("password"));
        }
        DBUtils.close(connection,preparedStatement,resultSet);
        return userInfo;
    }
}
