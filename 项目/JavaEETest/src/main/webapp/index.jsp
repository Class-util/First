<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<body>

<%
    Date date = new Date();
    String s = date.toString();
    String[] str = s.split(" ");
    String time = str[3];
    String[] hour = time.split(":");
    int num = Integer.parseInt(hour[0]);
%>

<%
    if(num >= 0 && num <= 10){
        out.println("早上好");
    }else {
        if(num > 10 && num <= 14){
            out.println("中午好");
        }else {
            if(num > 14 && num <= 19){
                out.println("下午好");
            }else {
                if(num > 19 && num <= 23){
                    out.println("晚上好");
                }
            }
        }
    }
%>

</body>
</html>
