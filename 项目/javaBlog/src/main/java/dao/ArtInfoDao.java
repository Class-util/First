package dao;

import models.ArtInfo;
import models.vo.ArticleInfoVO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 04 04
 * Time:17:09
 */
public class ArtInfoDao {

    //读取文章信息
    public List<ArtInfo> getAtrListByUID(int uid) throws SQLException {
        List<ArtInfo> list = new ArrayList<>();
        Connection connection = DBUtils.getConnection();
        String sql = "select * from articleinfo where uid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,uid);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            ArtInfo articleInfo = new ArtInfo();
            articleInfo.setId(resultSet.getInt("id"));
            articleInfo.setRcount(resultSet.getInt("rcount"));
            articleInfo.setTitle(resultSet.getString("title"));
            articleInfo.setContent(resultSet.getString("Content"));
            articleInfo.setCreatetime(resultSet.getDate("createtime"));
            list.add(articleInfo);
        }
        DBUtils.close(connection,preparedStatement,resultSet);
        return list;
    }

    //进行删除操作
    public int del(int id) throws SQLException {
        int ret= 0;
        if(id > 0){
            Connection connection = DBUtils.getConnection();
            String sql = "delete from articleinfo where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ret = preparedStatement.executeUpdate();
            DBUtils.close(connection,preparedStatement,null);
        }
        return ret;
    }
//根据id查询文章详情
    public ArticleInfoVO getArtById(int id) throws SQLException {
        ArticleInfoVO artInfovo = new ArticleInfoVO();
        if(id > 0){
            Connection connection = DBUtils.getConnection();
            String sql = " select a.*,u.username from articleinfo a left join userinfo u on a.uid=u.id where a.id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                artInfovo.setId(resultSet.getInt("id"));
                artInfovo.setTitle(resultSet.getString("title"));
                artInfovo.setContent(resultSet.getString("content"));
                artInfovo.setUsername(resultSet.getString("username"));
                artInfovo.setCreatetime(resultSet.getDate("createtime"));
                artInfovo.setRcount(resultSet.getInt("rcount"));
            }
            DBUtils.close(connection,preparedStatement,resultSet);
        }
        return artInfovo;
    }

    //文章修改
    public int uoArt(int id, String title, String content) throws SQLException {
        int ret = 0;
        if(id > 0 && title!= null && content != null && !title.equals("") && !content.equals("")){
            Connection connection = DBUtils.getConnection();
            String sql = "update articleinfo set title = ?,content = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,content);
            preparedStatement.setInt(3,id);
            ret = preparedStatement.executeUpdate();
            DBUtils.close(connection,preparedStatement,null);
        }
        return ret;
    }

    //文章添加
    public int add(String title, String content, int uid) throws SQLException {
        int ret = 0;
        if(title!= null && content != null && !title.equals("") && !content.equals("")){
            Connection connection = DBUtils.getConnection();
            String sql = "insert into articleinfo(title,content,uid) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,content);
            preparedStatement.setInt(3,uid);

            ret = preparedStatement.executeUpdate();
            DBUtils.close(connection,preparedStatement,null);
        }
        return ret;
    }

    //查询所有文章
    public List<ArticleInfoVO> getLiet() throws SQLException {
        List<ArticleInfoVO> list = new ArrayList<>();
        Connection connection = DBUtils.getConnection();
        String sql = "select a.*,u.username from articleinfo a left join userinfo u on a.uid=u.id;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            ArticleInfoVO vo  = new ArticleInfoVO();
            vo.setId(resultSet.getInt("id"));
            vo.setUsername(resultSet.getString("username"));
            vo.setTitle(resultSet.getString("title"));
            vo.setCreatetime(resultSet.getDate("createtime"));
            vo.setRcount(resultSet.getInt("rcount"));
            list.add(vo);
        }
        DBUtils.close(connection,preparedStatement,resultSet);
        return list;
    }

    //分页查找
    public List<ArticleInfoVO> getLietByPage(int page, int psize) throws SQLException {
        List<ArticleInfoVO> list = new ArrayList<>();
        Connection connection = DBUtils.getConnection();
        String sql = "select a.*,u.username from articleinfo a left join userinfo u on a.uid=u.id limit ?,?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,(page-1)*psize);
        preparedStatement.setInt(2,psize);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            ArticleInfoVO vo  = new ArticleInfoVO();
            vo.setId(resultSet.getInt("id"));
            vo.setUsername(resultSet.getString("username"));
            vo.setTitle(resultSet.getString("title"));
            vo.setCreatetime(resultSet.getDate("createtime"));
            vo.setRcount(resultSet.getInt("rcount"));
            list.add(vo);
        }
        DBUtils.close(connection,preparedStatement,resultSet);
        return list;
    }

    //阅读量加1
    public int upRcount(int id) throws SQLException {
        int ret = 0;
        if(id > 0){
            Connection connection = DBUtils.getConnection();
            String sql = "update articleinfo set rcount = rcount+1 where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ret = preparedStatement.executeUpdate();
            DBUtils.close(connection,preparedStatement,null);
        }
        return ret;
    }
}
