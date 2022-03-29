package com.lt.health.controller;

import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.constant.UserConstant;
import com.lt.health.entity.User;
import com.lt.health.entity.dto.LoginUserDTO;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.service.UserService;
import com.lt.health.utils.RedisUtil;
import com.lt.health.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


/**
 * @description: 用户控制器
 * @author: 狂小腾
 * @date: 2022/3/21 22:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录接口
     *
     * @param loginUserDTO 登录参数请求体
     * @return 返回token，用token去获取资源
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginUserDTO loginUserDTO) {
        return userService.login(loginUserDTO);
    }

    /**
     * 获取用户脱敏后的信息
     */
    @GetMapping("/getInfo")
    public Result getUserInfo() {
        User user = SecurityUtil.getUser();
        return Result.success("获取用户信息成功！", user);
    }

    /**
     * 用户退出登录
     */
    @GetMapping("/logout")
    public Result logout() {
        // 清除缓存
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return Result.success(MessageConstant.LOGOUT_SUCCESS);
    }

    /**
     * 根据(查询条件---用户名)分页查询
     *
     * @param pageInfoDTO 分页参数
     * @return 分页结果集
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.USER_SELECT_FAIL);
        }
        return userService.findPage(pageInfoDTO);
    }

    /**
     * 添加用户接口
     *
     * @param user 添加用户信息
     * @return 成功或者失败信息
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody User user) {
        String userName = user.getUserName();
        String userPassword = user.getPassword();
        Integer sex = user.getSex();
        String phoneNumber = user.getPhoneNumber();
        String email = user.getEmail();
        // 判断字符不能为空
        if (StringUtils.isAnyBlank(userName, userPassword, phoneNumber, email)) {
            return Result.fail(MessageConstant.COMPLETE_USER_INFO);
        }
        if (sex == null) {
            return Result.fail(MessageConstant.COMPLETE_USER_INFO);
        }
        if (userName.length() < 2 || userName.length() > 20) {
            return Result.fail("用户名长度在2到20个字符之间！！！");
        }
        if (userPassword.length() < 6 || userPassword.length() > 20) {
            return Result.fail("密码长度在6到20个字符之间！！！");
        }
        // 添加用户
        return userService.insert(user);
    }

    /**
     * 更新用户
     *
     * @param user 更新的用户信息
     * @return 修改提示信息
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        // TODO 判断字段
        return userService.update(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除提示信息
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
