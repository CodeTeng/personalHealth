package com.lt.health;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动
 *
 * @author 狂小腾
 */
@SpringBootApplication
@MapperScan("com.lt.health.mapper")
public class PersonalHealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalHealthApplication.class, args);
    }

}
