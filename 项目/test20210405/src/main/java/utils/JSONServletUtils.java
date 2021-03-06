package utils;


import com.fasterxml.jackson.databind.json.JsonMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 05
 * Time:18:31
 */
public class JSONServletUtils {
    public static void write(HttpServletResponse response, HashMap<String,Object> map) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonMapper mapper = new JsonMapper();
        writer.println(mapper.writeValueAsString(map));
    }
}
