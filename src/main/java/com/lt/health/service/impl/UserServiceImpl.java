package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.entity.User;
import com.lt.health.mapper.UserMapper;
import com.lt.health.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author 狂小腾
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-03-21 22:07:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




