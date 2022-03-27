package com.lt.health.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 菜单控制器
 * @author: 狂小腾
 * @date: 2022/3/22 11:07
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

//    @Autowired
//    private MenuService menuService;
//
//    /**
//     * 根据用户Id查询菜单列表
//     */
//    @GetMapping("/findMenu")
//    public List<Menu> findMenu() {
//        // TODO 需要根据权限查询
//        return null;
//    }
//
//    /**
//     * 分页查询菜单信息---根据标题
//     */
//    @PostMapping("/findPage")
//    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
//        return menuService.findPage(pageInfoDTO);
//    }
//
//    /**
//     * 添加菜单信息
//     */
//    @PostMapping("/insert")
//    public Result insert(@RequestBody Menu menu) {
//        if (menu == null) {
//            return Result.fail(400, MessageConstant.MENU_INSERT_FAIL);
//        }
//        menuService.save(menu);
//        return Result.success(200, MessageConstant.MENU_INSERT_SUCCESS);
//    }
//
//    /**
//     * 删除菜单信息
//     */
//    @DeleteMapping("/delete/{id}")
//    public Result delete(@PathVariable("id") Integer id) {
//        if (id < 1) {
//            return Result.fail(400, MessageConstant.MENU_NO_EXIST);
//        }
//        menuService.removeById(id);
//        return Result.success(200, MessageConstant.MENU_DELETE_SUCCESS);
//    }
//
//    /**
//     * 修改菜单信息
//     */
//    @PostMapping("/update")
//    public Result update(@RequestBody Menu menu) {
//        if (menu == null) {
//            return Result.fail(400, MessageConstant.MENU_UPDATE_FAIL);
//        }
//        menuService.updateById(menu);
//        return Result.success(200, MessageConstant.MENU_UPDATE_SUCCESS);
//    }

}
