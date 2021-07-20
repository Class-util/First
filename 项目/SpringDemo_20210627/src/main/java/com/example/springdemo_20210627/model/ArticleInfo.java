package com.example.springdemo_20210627.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 19
 * Time:17:14
 */
@Data
public class ArticleInfo {
    private int id;
    private String title;
    private String content;
    private Date createtime;
    private Date updatetime;
    private int uid;
    private int rcount;
    private int state;

    private User user;
}
