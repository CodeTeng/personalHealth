package com.lt.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.constant.UserConstant;
import com.lt.health.entity.Menu;
import com.lt.health.entity.Permission;
import com.lt.health.entity.Role;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.mapper.*;
import com.lt.health.service.RoleService;
import com.lt.health.utils.RedisUtil;
import com.lt.health.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_role】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolesPermissionsMapper rolesPermissionsMapper;

    @Autowired
    private RolesMenusMapper rolesMenusMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Result findAll() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id, label");
        queryWrapper.eq("status", 1);
        List<Role> roles = roleMapper.selectList(queryWrapper);
        return Result.success(MessageConstant.ROLE_SELECT_SUCCESS, roles);
    }

    @Override
    public Result findPage(PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        log.info("分页查询--> 页码==>{}, 页数大小==>{}", pageNumber, pageSize);
        Page<Role> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(queryString), "label", queryString)
                .or().like(StringUtils.isNotEmpty(queryString), "code", queryString);
        Page<Role> rolePage = roleMapper.selectPage(page, queryWrapper);
        long total = rolePage.getTotal();
        log.info("分页总数据：{}", total);
        List<Role> records = rolePage.getRecords();
        records.forEach(role -> {
            log.info("查询角色下菜单信息");
            List<Menu> menus = menuMapper.findByRoleId(role.getId());
            menus.forEach(menu -> {
                log.info("根据角色id和菜单父id查询子菜单");
                List<Menu> children = menuMapper.findByRoleIdAndParentId(role.getId(), menu.getId());
                menu.setChildren(children);
            });
            role.setMenus(menus);
            log.info("查询角色权限信息");
            List<Permission> permissions = permissionMapper.findByRoleId(role.getId());
            role.setPermissions(permissions);
        });
        return Result.success(MessageConstant.PAGE_SUCCESS, rolePage);
    }

    @Transactional
    @Override
    public Result insert(Role role) {
        log.info("查询角色信息是否存在");
        Long roleId = role.getId();
        Role findRole = roleMapper.selectById(roleId);
        if (findRole != null) {
            return Result.fail(MessageConstant.ROLE_EXIST);
        }
        log.info("插入角色信息");
        roleMapper.insert(role);
        if (role.getPermissions().size() > 0) {
            log.info("再添加对应的权限数据");
            role.getPermissions().forEach(permission -> rolesPermissionsMapper.insertRolesPermissions(roleId, permission.getId()));
        }
        if (role.getMenus().size() > 0) {
            log.info("再添加对应的菜单数据");
            role.getMenus().forEach(menu -> rolesMenusMapper.insertRolesMenus(roleId, menu.getId()));
        }
        // TODO ??? 不明白
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        return Result.success(MessageConstant.ROLE_INSERT_SUCCESS);
    }

    @Transactional
    @Override
    public Result update(Role role) {
        log.info("更新角色信息");
        roleMapper.updateById(role);
        Long roleId = role.getId();
        if (role.getPermissions().size() > 0) {
            log.info("先删除对应的权限信息");
            rolesPermissionsMapper.deletePermissionByRoleId(roleId);
            log.info("再添加对应的权限信息");
            role.getPermissions().forEach(permission -> rolesPermissionsMapper.insertRolesPermissions(roleId, permission.getId()));
        }
        if (role.getMenus().size() > 0) {
            log.info("先删除对应的菜单信息");
            rolesMenusMapper.deleteMenusByRoleId(roleId);
            log.info("再添加对应的菜单信息");
            role.getMenus().forEach(menu -> rolesMenusMapper.insertRolesMenus(roleId, menu.getId()));
        }
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        return Result.success(MessageConstant.ROLE_UPDATE_SUCCESS);
    }

    @Override
    public Result delete(Long roleId) {
        log.info("查询角色信息下是否有菜单权限");
        List<Menu> menus = menuMapper.findByRoleId(roleId);
        List<Menu> children = new ArrayList<>();
        menus.forEach(menu -> children.addAll(menuMapper.findByRoleIdAndParentId(roleId, menu.getId())));
        if (menus.size() > 0 || children.size() > 0) {
            log.info("该角色信息下菜单权限，无法删除");
            return Result.fail(MessageConstant.ROLE_DELETE_TIP_MENU);
        }
        List<Permission> permissions = permissionMapper.findByRoleId(roleId);
        if (permissions.size() > 0) {
            log.info("该角色信息下有数据权限，无法删除");
            return Result.fail(MessageConstant.ROLE_DELETE_TIP_PERMISSION);
        }
        roleMapper.deleteById(roleId);
        redisUtil.delKey(UserConstant.USER_KEY_PRE + SecurityUtil.getUsername());
        return Result.success(MessageConstant.ROLE_DELETE_SUCCESS);
    }
}




