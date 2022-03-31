package com.lt.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.constant.UserConstant;
import com.lt.health.entity.Menu;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.mapper.MenuMapper;
import com.lt.health.service.MenuService;
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
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result findParentMenu() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parent_id");
        List<Menu> menus = menuMapper.selectList(queryWrapper);
        return Result.success(MessageConstant.PARENT_MENU_SELECT_SUCCESS, menus);
    }

    @Override
    public Result findPage(PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        log.info("开始数据分页-->页码{}, --->{}页数--->查询内容{}", pageNumber, pageSize, queryString);
        Page<Menu> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.like(StringUtils.isNotEmpty(queryString), "title", queryString);
        Page<Menu> menuPage = menuMapper.selectPage(page, query);
        long total = menuPage.getTotal();
        List<Menu> records = menuPage.getRecords();
        log.info("查询的总条数-->{}", total);
        log.info("分页列表-->{}", records);
        return Result.success(MessageConstant.PAGE_SUCCESS, menuPage);
    }

    @Transactional
    @Override
    public Result insert(Menu menu) {
        log.info("添加菜单信息");
        menuMapper.insert(menu);
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        return Result.success(MessageConstant.MENU_INSERT_SUCCESS);
    }

    @Transactional
    @Override
    public Result update(Menu menu) {
        log.info("修改菜单信息");
        menuMapper.updateById(menu);
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        return Result.success(MessageConstant.MENU_UPDATE_SUCCESS);
    }

    @Override
    public Result delete(long id) {
        log.info("删除菜单信息");
        menuMapper.deleteById(id);
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        return Result.success(MessageConstant.MENU_DELETE_SUCCESS);
    }
}




