package com.example.springdemo_20210627.controller;

import com.example.springdemo_20210627.mapper.ArticleInfoMapper;
import com.example.springdemo_20210627.model.ArticleInfo;
import com.example.springdemo_20210627.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 20
 * Time:20:57
 */
@RestController
@RequestMapping("/art")
public class ArticleInfoController {

    @Resource
    private ArticleInfoMapper articleInfoMapper;

    @RequestMapping("/del")
    public int deleteBuId (@RequestParam int id){
        return articleInfoMapper.delArticleById(id);
    }

    @RequestMapping("/detail")
    public ArticleInfo detail(@RequestParam int id){
        return articleInfoMapper.detail(id);
    }

    @RequestMapping("/add")
    public ArticleInfo add(@RequestParam String title, @RequestParam String content, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            return null;
        }
        Object user = session.getAttribute("user");
        User u = (User) user;
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle(title);
        articleInfo.setContent(content);
        articleInfo.setUid(u.getId());
        articleInfoMapper.add(articleInfo);
        return articleInfo;
    }

}
