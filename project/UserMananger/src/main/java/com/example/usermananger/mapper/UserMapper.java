package com.example.usermananger.mapper;

import com.example.usermananger.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 22
 * Time:17:55
 */
@Mapper
public interface UserMapper {

    public UserInfo login(String username,String password);

    public int add(UserInfo userInfo);

    public UserInfo getUser(int uid);

    public int update(UserInfo userInfo);

    public List<UserInfo> getListByPage(String name, String address, String email, int skipcount, int psize,
                                        Integer isadmin);

    public int getCount(String name, String address, String email,Integer isadmin);

    public int del(int id, Integer isadmin);

    public int dels(String[] ids);
}
