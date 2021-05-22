package org.example.exception;

/**
 * Created with IntelliJ IDEA.
 * Description:将报错信息输送给前端
 * User:吴博
 * Date:2021 05 20
 * Time:21:16
 */
public class AppException extends RuntimeException{
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
