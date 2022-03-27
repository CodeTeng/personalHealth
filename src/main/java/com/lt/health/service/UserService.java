package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.entity.dto.LoginUserDTO;

/**
* @author 狂小腾
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2022-03-26 20:36:16
*/
public interface UserService extends IService<User> {

    /**
     * 登录接口
     *
     * @param loginUserDTO 登录参数请求体
     * @return 返回token，用token去获取资源
     */
    Result login(LoginUserDTO loginUserDTO);
}
