package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 05 20
 * Time:17:56
 */
@Getter
@Setter
@ToString
//数据库使用，前后端ajax，session保存时是基于对象和二进制数据转换（这里要实现串行化接口）
public class User extends Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String name;
    private String password;
    private String nickName;
    private String iconPath;
    private String signature;
    private java.util.Date lastLogout;

}
