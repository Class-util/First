package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 20
 * Time:16:51
 */
public class Util {

    private static final ObjectMapper M = new ObjectMapper();

    private static final MysqlDataSource DATA_SOURCE = new MysqlDataSource();

    static {
        //日期格式化,设置json序列化及反序列化的日期格式
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        M.setDateFormat(df);
        //数据库操作
        DATA_SOURCE.setURL("jdbc:mysql://localhost:3306/java_chatroom");
        DATA_SOURCE.setUser("root");
        DATA_SOURCE.setPassword("120917981");
        DATA_SOURCE.setUseSSL(false);
        //设置编码格式
        DATA_SOURCE.setCharacterEncoding("UTF-8");
    }

    /**
     * json序列化：java对象转换为json字符串
     * */

    public static String serialize(Object o){
        try {
            return M.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON序列化失败：" + o , e);
        }
    }

    /**
     * 反序列化json:把json字符串转为java对象
     * */

    public static <T> T deserialize(String s,Class<T> c){
        try {
            return M.readValue(s,c);
        } catch (JsonProcessingException e) {
            //如果出现异常，一般都是json字符串中的键，在class中没有找到对应的属性
            throw new RuntimeException("JSON反序列化失败：" , e);
        }
    }

    /**
     * 获取数据库连接
     * */

    public static Connection getConnection(){
        try {
            return DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库连接失败" + e);
        }
    }

    /**
     * 释放jdbc资源
     * */

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            if(resultSet != null) resultSet.close();
            if(preparedStatement != null) preparedStatement.close();
            if(connection != null) connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("释放数据库资源出错" + e);
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement){
        close(connection,preparedStatement,null);
    }


    public static void main(String[] args) {
        //测试json序列化
        Map<String,Object> map = new HashMap<>();
        map.put("ok",true);
        map.put("d",new Date());
        System.out.println(serialize(map));

        //测试数据库连接
        System.out.println(getConnection());
    }

}
