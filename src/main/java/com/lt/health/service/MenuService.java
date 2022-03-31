package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.entity.dto.PageInfoDTO;

/**
 * @author 狂小腾
 * @description 针对表【sys_menu】的数据库操作Service
 * @createDate 2022-03-26 20:36:16
 */
public interface MenuService extends IService<Menu> {

    /**
     * 分页查询
     *
     * @param pageInfoDTO 查询条件
     * @return 分页结果
     */
    Result findPage(PageInfoDTO pageInfoDTO);

    /**
     * 添加菜单接口
     *
     * @param menu 添加菜单信息
     * @return 成功或者失败信息
     */
    Result insert(Menu menu);

    /**
     * 更新菜单接口
     *
     * @param menu 更新的菜单信息
     * @return 修改提示信息
     */
    Result update(Menu menu);

    /**
     * 删除菜单接口
     *
     * @param id 菜单id
     * @return 删除提示信息
     */
    Result delete(long id);

    /**
     * 查询所有的父级菜单
     *
     * @return 父级菜单
     */
    Result findParentMenu();
}
