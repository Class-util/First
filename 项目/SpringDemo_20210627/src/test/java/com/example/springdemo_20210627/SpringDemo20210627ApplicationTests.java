package com.example.springdemo_20210627;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDemo20210627ApplicationTests {

    //读取配置文件中的信息
    @Value("${myimagpath}")
    private String imgpath;


    @Test
    void contextLoads() {
        System.out.println(imgpath);
    }

}
