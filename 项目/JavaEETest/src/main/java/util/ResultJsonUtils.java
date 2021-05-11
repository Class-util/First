package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:json的常用方法
 * User:吴博
 * Date:2021 05 11
 * Time:15:00
 */
public class ResultJsonUtils {
    public static void write(HttpServletResponse response, HashMap<String,Object> map) throws IOException {
        /*//声明返回页面信息的编码格式
        response.setCharacterEncoding("utf-8");
        //声明返回页面的类型
        response.setContentType("application/json");
        //获取输入流
        PrintWriter writer = response.getWriter();
        JsonMapper mapper = new JsonMapper();
        writer.println(mapper.writeValueAsString(mapper));*/
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        //ObjectMapper mapper1 = new ObjectMapper();
        JsonMapper mapper = new JsonMapper();
        writer.println(mapper.writeValueAsString(map));
    }
}
