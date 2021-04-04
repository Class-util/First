package dao;

import models.ArtInfo;
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
            articleInfo.setRcount(resultSet.getString("rcount"));
            articleInfo.setTitle(resultSet.getString("title"));
            articleInfo.setContent(resultSet.getString("Content"));
            articleInfo.setCreatetime(resultSet.getDate("createtime"));
            list.add(articleInfo);
        }
        return list;
    }
}
