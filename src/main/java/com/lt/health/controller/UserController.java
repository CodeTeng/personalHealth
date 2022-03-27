package com.lt.health.controller;

import com.lt.health.constant.Result;
import com.lt.health.entity.dto.LoginUserDTO;
import com.lt.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

//    /**
//     * 根据用户名分页查询
//     *
//     * @param pageInfoDTO 分页参数
//     * @return 结果集
//     */
//    @PostMapping("/findPage")
//    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
//        String queryInfo = pageInfoDTO.getQueryInfo();
//        Integer pageNum = pageInfoDTO.getPageNum();
//        Integer pageSize = pageInfoDTO.getPageSize();
//        if (StringUtils.isAnyBlank(queryInfo, String.valueOf(pageNum), String.valueOf(pageSize))) {
//            return Result.fail(400, MessageConstant.USER_SELECT_FAIL);
//        }
//        return userService.findPage(pageInfoDTO);
//    }
//
//    /**
//     * 增加用户信息
//     *
//     * @param user 添加用户
//     * @return 结果集
//     */
//    @PostMapping("/add")
//    public Result addUser(@RequestBody User user) {
//        if (user == null) {
//            return Result.fail(400, MessageConstant.ADD_USER_FAIL);
//        }
//        return userService.addUser(user);
//    }
//
//    /**
//     * 更新用户信息
//     *
//     * @param user 更新用户
//     * @return 结果集
//     */
//    @PostMapping("/update")
//    public Result updateUser(@RequestBody User user) {
//        if (user == null) {
//            return Result.fail(400, MessageConstant.UPDATE_USER_FAIL);
//        }
//        return userService.updateUser(user);
//    }
}
