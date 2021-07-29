package utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 04
 * Time:11:18
 */
public class DBUtils {

    private static MysqlDataSource dataSource = null;

    //连接数据库
    public static Connection getConnection() throws SQLException {
        if(dataSource == null){
            //第一次调用
            dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://127.0.0.1:3306/javablog?charactionEncoding=utf-8");
            dataSource.setUser("root");
            dataSource.setPassword("120917981");
        }
        return dataSource.getConnection();
    }

    //关闭方法
    public static void close(Connection connection, PreparedStatement preparedStatement , ResultSet resultSet) throws SQLException {
        if(resultSet != null)
            resultSet.close();
        if(preparedStatement != null)
            preparedStatement.close();
        if(connection != null)
            connection.close();
    }

}
