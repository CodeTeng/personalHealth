package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.config.security.service.UserDetailServiceImpl;
import com.lt.health.constant.Result;
import com.lt.health.entity.User;
import com.lt.health.entity.dto.LoginUserDTO;
import com.lt.health.mapper.UserMapper;
import com.lt.health.service.UserService;
import com.lt.health.utils.JwtTokenUtil;
import com.lt.health.utils.MD5Util;
import com.lt.health.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 狂小腾
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private RedisUtils redisUtil;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public Result login(LoginUserDTO loginUserDTO) {
        UserDetails userDetails;
        // 1.判断用户是以哪种方式进行登录
        String type = loginUserDTO.getType();
        String phoneNumber = loginUserDTO.getPhoneNumber();
        String code = loginUserDTO.getCode();
        String username = loginUserDTO.getUsername();
        String password = loginUserDTO.getPassword();
        if ("2".equals(type)) {
            // 2 手机验证码进行登录
            // 2.1 请求判断参数
            if (StringUtils.isAnyBlank(phoneNumber, code)) {
                return Result.fail("请填写完整的信息！！！");
            }
            // 2.2 从redis中获取进行验证码对比
            Object findCode = redisUtil.getValue(phoneNumber + "sms");
            if (findCode == null) {
                return Result.fail("验证码已经过期！");
            }
            if (!findCode.equals(code)) {
                return Result.fail("验证码不正确！");
            }
            // 2.3 用security进行登录
            userDetails = userDetailService.loadUserByUsername(phoneNumber);
        } else {
            // 3.账号密码登录
            if (StringUtils.isAnyBlank(username, password)) {
                return Result.fail("请填写完整的信息！！！");
            }
            log.info("3.1 开始登录");
            // 3.1 用security进行登录
            userDetails = userDetailService.loadUserByUsername(username);
            log.info("3.2 判断该用户是否存在，密码是否正确");
            System.out.println("==================>" + userDetails.getPassword());
            System.out.println("==================>" + MD5Util.md5(password));
            if (userDetails == null || !passwordEncoder.matches(MD5Util.md5(password), userDetails.getPassword())) {
                return Result.fail("账号或者密码错误！！！");
            }
        }
        // 4.判断用户是否被禁用
        if (!userDetails.isEnabled()) {
            return Result.fail("该账号未被启用，请联系管理员");
        }
        // 5.在security中存入登陆者的信息
        log.info("登录成功，在security对象中存入登陆者的信息");
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 6.借助jwt来生成token
        log.info("根据登陆者信息生成jwt");
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> map = new HashMap<>(2);
        map.put("tokenHead", tokenHead);
        map.put("token", token);
        return Result.success("登录成功！", map);
    }
}

