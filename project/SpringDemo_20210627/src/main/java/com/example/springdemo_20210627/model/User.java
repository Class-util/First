package com.example.springdemo_20210627.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 06 27
 * Time:15:14
 */
@Getter
@Setter
@Controller
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String photo;
    private List<ArticleInfo> alist;
}
