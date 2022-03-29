package com.lt.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Role;
import com.lt.health.service.RoleService;
import com.lt.health.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 狂小腾
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2022-03-26 20:36:16
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Result findAll() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id, label");
        queryWrapper.eq("status", 1);
        List<Role> roles = roleMapper.selectList(queryWrapper);
        return Result.success(MessageConstant.ROLE_SELECT_SUCCESS, roles);
    }
}




