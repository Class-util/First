package util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.omg.Dynamic.Parameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:操作数据库
 * User:吴博
 * Date:2021 05 11
 * Time:14:52
 */
public class DButils {

    public static MysqlDataSource dataSource = null;
    //获取connection对象
    public static Connection getConnection() throws SQLException {
        //第一次调用
        if(dataSource == null){
            dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://127.0.0.1:3306/JavaEETest?charactionEncoding=utf-8");
            dataSource.setUser("root");
            dataSource.setPassword("120917981");
        }
        return dataSource.getConnection();
    }
    //关闭方法
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if(resultSet != null){
            resultSet.close();
        }
        if(preparedStatement != null){
            preparedStatement.close();
        }
        if(connection != null){
            connection.close();
        }
    }
}
