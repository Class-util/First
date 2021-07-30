package com.example.springdemo_20210627.mapper;

import com.example.springdemo_20210627.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:吴博
 * Date:2021 07 19
 * Time:0:27
 */
@SpringBootTest
//表示当执行完业务操作后，进行回滚操作
//@Transactional
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void addUser() {
        User user = new User();
        user.setUsername("唐僧");
        user.setPassword("456");
        user.setPhoto("789");
        int res = userMapper.addUser(user);
        System.out.println(res);
    }

    @Test
    void getUserById() {
        User user = userMapper.getUserById(17);
        System.out.println(user.getUsername() + ":" + user.getPassword());
    }

    @Test
    void getUserByNameAndPassword() {
        User user = userMapper.getUserByNameAndPassword("唐僧","123");
        System.out.println(user);
    }

    @Test
    void getAll() {
        List<User> list = userMapper.getAll();
        System.out.println(list);
    }

    @Test
    void delById() {
        int res = userMapper.delById(0);
        System.out.println(res);
    }

    @Test
    void upUser() {
        int res = userMapper.upUser(17, "猪八戒");
        System.out.println(res);
    }

    @Test
    void getList() {
        List<User> list = userMapper.getList("desc");
        System.out.println(list);
    }

    @Test
    void getListByName() {
        List<User> list = userMapper.getListByName("ro");
        System.out.println(list);
    }

    @Test
    void getListByName1() {
        String username = "%' or username = '%";
        //String username = "ro";
        List<User> list = userMapper.getListByName2(username);
        System.out.println(list);
    }

    @Test
    void getFullUser() {
        User user = userMapper.getFullUser(1);
        System.out.println(user);
    }
}