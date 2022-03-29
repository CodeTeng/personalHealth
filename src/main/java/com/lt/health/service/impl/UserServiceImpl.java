package com.lt.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.config.security.service.UserDetailServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Role;
import com.lt.health.entity.User;
import com.lt.health.entity.UserRoles;
import com.lt.health.entity.dto.LoginUserDTO;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.mapper.RoleMapper;
import com.lt.health.mapper.UserMapper;
import com.lt.health.mapper.UserRolesMapper;
import com.lt.health.service.UserService;
import com.lt.health.utils.JwtTokenUtil;
import com.lt.health.utils.MD5Util;
import com.lt.health.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    private RedisUtil redisUtil;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

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
            String regex = "^1[3-9]\\d{9}$";
            if (!Pattern.matches(regex, phoneNumber)) {
                return Result.fail("请填写正确的手机号码！！！");
            }
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
            if (username.length() < 2 || username.length() > 20) {
                return Result.fail("用户名长度在2到20个字符之间！！！");
            }
            if (password.length() < 6 || password.length() > 20) {
                return Result.fail("密码长度在6到20个字符之间！！！");
            }
            log.info("3.1 开始登录");
            // 3.1 用security进行登录
            userDetails = userDetailService.loadUserByUsername(username);
            log.info("3.2 判断该用户是否存在，密码是否正确");
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
        log.info(userDetails.getAuthorities().toString());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 6.借助jwt来生成token
        log.info("根据登陆者信息生成jwt");
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> map = new HashMap<>(2);
        map.put("tokenHead", tokenHead);
        map.put("token", token);
        return Result.success("登录成功！", map);
    }

    @Override
    public void updatePasswordByMail(String email, String password) {
        log.info("根据邮箱来更改密码");
        userMapper.updatePasswordByMail(email, password);
    }

    @Override
    public Result findPage(PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        log.info("分页查询--> 页码==>{}, 页数大小==>{}", pageNumber, pageSize);
        Page<User> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // sql语句查询
        queryWrapper.like(StringUtils.isNotEmpty(queryString), "user_name", queryString)
                .or().like(StringUtils.isNotEmpty(queryString), "nick_name", queryString)
                .or().like(StringUtils.isNotEmpty(queryString), "phone_number", queryString);
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        List<User> records = userPage.getRecords();
        // 用户脱敏与设置权限与角色数据(数据库中没有该字段)
        records.forEach(user -> {
            user.setRoles(roleMapper.findRolesByUserId(user.getId()));
            user.setPassword(null);
            user.setOpenId(null);
        });
        return Result.success(MessageConstant.PAGE_SUCCESS, userPage);
    }

    @Transactional
    @Override
    public Result insert(User user) {
        log.info("1.根据用户名查询用户信息");
        String userName = user.getUserName();
        User findUser = userMapper.findByUserName(userName);
        if (findUser != null) {
            // 已经有该用户 不能再重复添加
            return Result.fail(MessageConstant.USER_EXIST);
        }
        // 不存在该用户 给密码进行加密
        user.setPassword(passwordEncoder.encode(MD5Util.md5(user.getPassword())));
        log.info("2.添加用户信息");
        userMapper.insert(user);
        log.info("3.添加用户角色");
        List<Role> roles = user.getRoles();
        if (roles.size() > 0) {
            roles.forEach(role -> userRolesMapper.insertUserRoles(user.getId(), role.getId()));
        }
        log.info("4.用户添加角色成功有{}个", roles.size());
        return Result.success(MessageConstant.ADD_USER_SUCCESS);
    }

    @Transactional
    @Override
    public Result update(User user) {
        log.info("1.先将用户角色信息给删除");
        QueryWrapper<UserRoles> queryWrapper = new QueryWrapper<>();
        Long userId = user.getId();
        queryWrapper.eq("userId", userId);
        userRolesMapper.delete(queryWrapper);
        log.info("2. 添加用户角色信息");
        List<Role> roles = user.getRoles();
        if (roles.size() > 0) {
            roles.forEach(role -> userRolesMapper.insertUserRoles(userId, role.getId()));
        }
        log.info("3. 修改用户信息");
        int result = userMapper.updateById(user);
        if (result == 0) {
            return Result.fail(MessageConstant.UPDATE_USER_FAIL);
        }
        return Result.success(MessageConstant.UPDATE_USER_SUCCESS);
    }

    @Override
    public Result delete(Long id) {
        log.info("1.判断删除的用户id是否存在");
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.fail(MessageConstant.USER_NO_EXIST);
        }
        log.info("2.删除用户角色信息");
        QueryWrapper<UserRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", id);
        userRolesMapper.delete(queryWrapper);
        log.info("3.删除用户信息");
        userMapper.deleteById(id);
        return Result.success(MessageConstant.DELETE_USER_SUCCESS);
    }
}

