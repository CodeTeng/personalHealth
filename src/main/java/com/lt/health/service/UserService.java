package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.entity.dto.LoginUserDTO;
import com.lt.health.entity.dto.PageInfoDTO;

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

    /**
     * 根据邮箱更新密码
     *
     * @param email    邮箱
     * @param password 加密后的密码
     */
    void updatePasswordByMail(String email, String password);

    /**
     * 根据(查询条件---用户名、昵称、手机号码)分页查询
     *
     * @param pageInfoDTO 分页参数
     * @return 分页结果集
     */
    Result findPage(PageInfoDTO pageInfoDTO);

    /**
     * 添加用户接口
     *
     * @param user 添加用户信息
     * @return 成功或者失败信息
     */
    Result insert(User user);

    /**
     * 更新用户
     *
     * @param user 更新的用户信息
     * @return 提示信息
     */
    Result update(User user);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除提示信息
     */
    Result delete(Long id);

    /**
     * WX小程序登录
     *
     * @param openid     WX用户的唯一标识
     * @param sessionKey 会话密钥
     * @return token openid sessionKey
     */
    Result miniLogin(String openid, String sessionKey);

    /**
     * 根据openid更新用户信息
     *
     * @param user 用户信息
     * @return 成功或失败信息
     */
    Result updateInfoByOpenid(User user);
}
