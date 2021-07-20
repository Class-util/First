package com.example.springdemo_20210627.mapper;

import com.example.springdemo_20210627.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 19
 * Time:17:21
 */
@Mapper
public interface ArticleInfoMapper {

    public List<ArticleInfo> getAll();

    public int addArticle(String title,String content,int uid,int rcount,int state);

    public List<ArticleInfo> getArticleIfo(String title,String content,int state);

    public List<ArticleInfo> getArticleIfo2(String title,String content,int state);

    public int upArticle(int id,String title,String content);

    public int delArticleByIds(int[] ids);

    public int delArticleById(int id);

    public ArticleInfo detail(int id);

    public int add(ArticleInfo articleInfo);
}
