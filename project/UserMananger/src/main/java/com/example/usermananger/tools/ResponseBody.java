package com.example.usermananger.tools;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:同一封装返回值
 * User:吴博
 * Date:2021 07 24
 * Time:9:20
 */
@Data
public class ResponseBody<T> {

    private int status;
    private String message;
    private T data;

    public ResponseBody(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
