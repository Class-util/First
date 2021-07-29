package com.example.springdemo_20210627.mapper;

import com.example.springdemo_20210627.model.ArticleInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 19
 * Time:17:53
 */
@SpringBootTest
@Transactional
class ArticleInfoMapperTest {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Test
    void getAll() {
        List<ArticleInfo> list = articleInfoMapper.getAll();
        list.forEach(System.out::println);
    }

    @Test
    void getArticleIfo() {
        List<ArticleInfo> list = articleInfoMapper.getArticleIfo(null, null, 0);
        list.forEach(System.out::println);
    }

    @Test
    void addArticle() {
        int i = articleInfoMapper.addArticle("星期天", "努力学习", 1, 0,0);
        System.out.println(i);
    }

    @Test
    void getArticleIfo2() {
        List<ArticleInfo> list = articleInfoMapper.getArticleIfo2("星期天", null, 1);
        list.forEach(System.out::println);
    }

    @Test
    void upArticle() {
        int res = articleInfoMapper.upArticle(3, "C++", "太难了");
        System.out.println(res);
    }

    @Test
    void delArticleByIds() {
        int[] arr = {1,2,3};
        int res = articleInfoMapper.delArticleByIds(arr);
        System.out.println(res);
    }
}