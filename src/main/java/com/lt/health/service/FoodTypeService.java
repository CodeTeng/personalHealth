package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.FoodType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 狂小腾
 * @description 针对表【sys_food_type(食物分类表)】的数据库操作Service
 * @createDate 2022-03-26 20:36:16
 */
public interface FoodTypeService extends IService<FoodType> {

    /**
     * 添加菜品分类
     *
     * @param foodType 菜品分类
     * @return 成功或失败信息
     */
    Result insertType(FoodType foodType);

    /**
     * 修改菜品分类
     *
     * @param foodType 菜品分类
     * @return 成功或失败信息
     */
    Result updateType(FoodType foodType);

    /**
     * 删除菜品分类
     *
     * @param id 菜品分类id
     * @return 成功或失败信息
     */
    Result deleteType(Long id);

    /**
     * 查询所有的菜品分类信息
     *
     * @return 菜品分类信息
     */
    Result typeAll();
}
