package com.example.springdemo_20210627.mapper;

import com.example.springdemo_20210627.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 18
 * Time:15:48
 */
@Mapper
@Repository
public interface UserMapper {

    //添加用户
    public int addUser(User user);

    //查询用户
    public User getUserById(int id);

    //通过用户名和密码查询
    public User getUserByNameAndPassword(@Param("name") String username, String password);

    //查询多条信息
    public List<User> getAll();

    //删除数据
    public int delById(int id);

    //更新数据
    public int upUser(int id,String username);

    //按序返回多条数据
    public List<User> getList(String ord);

    //模糊查询
    public List<User> getListByName(String username);

    public List<User> getListByName2(String username);

    public User getFullUser(int id);
}
