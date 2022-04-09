package com.lt.health.controller;

import com.lt.health.aop.log.SystemCrmLog;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.constant.TableNameConstant;
import com.lt.health.entity.Menu;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 菜单控制器
 * @author: 狂小腾
 * @date: 2022/3/22 11:07
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有的父级菜单
     *
     * @return 父级菜单
     */
    @GetMapping("/findParent")
    @ApiOperation("查询所有父级菜单接口")
    public Result findParentMenu() {
        return menuService.findParentMenu();
    }

    /**
     * 分页查询
     *
     * @param pageInfoDTO 查询条件---菜单标题
     * @return 分页结果
     */
    @PostMapping("/findPage")
    @ApiOperation("分页查询所有菜单接口")
    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.PAGE_FAIL);
        }
        return menuService.findPage(pageInfoDTO);
    }

    /**
     * 添加菜单接口
     *
     * @param menu 添加菜单信息
     * @return 成功或者失败信息
     */
    @PostMapping("/insert")
    @ApiOperation("添加菜单接口")
    @SystemCrmLog(description = "进行添加菜单操作", tableName = {TableNameConstant.MENU_TABLE_NAME})
    public Result insert(@RequestBody Menu menu) {
        return menuService.insert(menu);
    }

    /**
     * 更新菜单接口
     *
     * @param menu 更新的菜单信息
     * @return 修改提示信息
     */
    @PutMapping("/update")
    @ApiOperation("修改菜单接口")
    @SystemCrmLog(description = "进行更新菜单操作", tableName = {TableNameConstant.MENU_TABLE_NAME})
    public Result update(@RequestBody Menu menu) {
        return menuService.update(menu);
    }

    /**
     * 删除菜单接口
     *
     * @param id 菜单id
     * @return 删除提示信息
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除菜单接口")
    @SystemCrmLog(description = "进行删除菜单操作", tableName = {TableNameConstant.MENU_TABLE_NAME})
    public Result delete(@PathVariable("id") long id) {
        return menuService.delete(id);
    }
}
