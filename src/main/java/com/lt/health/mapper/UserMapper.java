package com.lt.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.health.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author 狂小腾
 * @description 针对表【sys_user】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.User
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象
     */
    User findByUserName(@Param("value") String username);

    /**
     * 根据邮箱更改用户密码
     *
     * @param email    邮箱
     * @param password 加密后的密码
     */
    @Update("update sys_user set user_password = #{password} where email = #{email}")
    void updatePasswordByMail(@Param("email") String email, @Param("password") String password);
}




