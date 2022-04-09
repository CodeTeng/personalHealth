package com.lt.health.controller;

import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Permission;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 数据权限控制器
 * @author: 狂小腾
 * @date: 2022/3/31 14:00
 */
@RestController
@RequestMapping("/permission")
@Api(tags = "数据权限接口")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有的权限
     *
     * @return 返回结果
     */
    @GetMapping("/findAll")
    @ApiOperation("查看所有权限接口")
    public Result findAll() {
        return permissionService.findAll();
    }

    /**
     * 分页查询
     *
     * @param pageInfoDTO 分页参数---权限标签或数据权限标签
     * @return 分页结果
     */
    @PostMapping("/findPage")
    @ApiOperation("数据权限分页查询接口")
    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.PAGE_FAIL);
        }
        return permissionService.findPage(pageInfoDTO);
    }

    /**
     * 添加权限
     *
     * @param permission 添加权限数据
     * @return 成功或失败结果
     */
    @PostMapping("/insert")
    @ApiOperation("添加数据权限接口")
    public Result insert(@RequestBody Permission permission) {
        return permissionService.insert(permission);
    }

    /**
     * 修改权限
     *
     * @param permission 修改的权限数据
     * @return 成功或失败的结果
     */
    @PutMapping("/update")
    @ApiOperation("修改数据权限接口")
    public Result update(@RequestBody Permission permission) {
        return permissionService.update(permission);
    }

    /**
     * 删除权限
     *
     * @param id 权限id
     * @return 成功或失败结果
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除数据权限接口")
    public Result delete(@PathVariable("id") Long id) {
        return permissionService.delete(id);
    }

}
