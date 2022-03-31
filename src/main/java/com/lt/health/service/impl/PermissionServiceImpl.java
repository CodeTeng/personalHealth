package com.lt.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.constant.UserConstant;
import com.lt.health.entity.Permission;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.mapper.PermissionMapper;
import com.lt.health.service.PermissionService;
import com.lt.health.utils.RedisUtil;
import com.lt.health.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_permission】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result findAll() {
        log.info("查询所有权限数据");
        List<Permission> permissions = permissionMapper.selectList(null);
        return Result.success(MessageConstant.PREMISSION_GET_SUCCESS, permissions);
    }

    @Override
    public Result findPage(PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        log.info("开始数据分页-->页码{}, --->{}页数--->查询内容{}", pageNumber, pageSize, queryString);
        Page<Permission> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Permission> query = new QueryWrapper<>();
        query.like(StringUtils.isNotEmpty(queryString), "label", queryString)
                .or().like(StringUtils.isNotEmpty(queryString), "code", queryString);
        Page<Permission> permissionPage = permissionMapper.selectPage(page, query);
        long total = permissionPage.getTotal();
        List<Permission> records = permissionPage.getRecords();
        log.info("查询的总条数-->{}", total);
        log.info("分页列表-->{}", records);
        return Result.success(MessageConstant.PAGE_SUCCESS, permissionPage);
    }

    @Transactional
    @Override
    public Result insert(Permission permission) {
        log.info("数据权限插入");
        permissionMapper.insert(permission);
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        return Result.success(MessageConstant.PREMISSION_INSERT_SUCCESS);
    }

    @Transactional
    @Override
    public Result update(Permission permission) {
        log.info("数据权限修改");
        permissionMapper.updateById(permission);
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        return Result.success(MessageConstant.PREMISSION_UPDATE_SUCCESS);
    }

    @Override
    public Result delete(Long id) {
        log.info("数据权限删除");
        permissionMapper.deleteById(id);
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        return Result.success(MessageConstant.PREMISSION_DELETE_SUCCESS);
    }
}




