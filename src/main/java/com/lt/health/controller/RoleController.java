package com.lt.health.controller;

import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Role;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 角色控制器
 * @author: 狂小腾
 * @date: 2022/3/28 22:42
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加用户时角色信息列表
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return roleService.findAll();
    }

    /**
     * 分页查询
     *
     * @param pageInfoDTO 分页参数
     * @return 分页结果集
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.ROLE_SELECT_FAIL);
        }
        return roleService.findPage(pageInfoDTO);
    }

    /**
     * 添加角色
     *
     * @param role 角色信息
     * @return 成功或者失败信息
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Role role) {
        String label = role.getLabel();
        String code = role.getCode();
        if (StringUtils.isAnyBlank(label, code)) {
            return Result.fail(MessageConstant.COMPLETE_USER_INFO);
        }
        return roleService.insert(role);
    }

    /**
     * 修改角色
     *
     * @param role 角色信息
     * @return 修改提示信息
     */
    @PutMapping("/update")
    public Result update(@RequestBody Role role) {
        return roleService.update(role);
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 删除提示信息
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long roleId) {
        return roleService.delete(roleId);
    }
}
