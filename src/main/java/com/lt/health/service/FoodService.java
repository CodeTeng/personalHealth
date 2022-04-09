package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.Food;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.entity.dto.PageInfoDTO;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_food(食物详情表)】的数据库操作Service
 * @createDate 2022-03-26 20:36:16
 */
public interface FoodService extends IService<Food> {

    /**
     * 通过excel批量导入食物
     *
     * @param list 食物列表
     * @return 提示信息
     */
    Result batchImport(List<Food> list);

    /**
     * 分页查询菜品分类和菜品信息
     *
     * @param pageInfoDTO 分页参数---菜品分类标题
     * @return 分页结果
     */
    Result findPage(PageInfoDTO pageInfoDTO);

    /**
     * 分页查询菜品信息
     *
     * @param pageInfoDTO 分页参数---食物名称标题
     * @return 分页结果
     */
    Result findFoodPage(PageInfoDTO pageInfoDTO);

    /**
     * 添加菜品
     *
     * @param food 菜品
     * @return 成功或失败信息
     */
    Result insert(Food food);

    /**
     * 修改菜品
     *
     * @param food 菜品
     * @return 成功或失败信息
     */
    Result update(Food food);

    /**
     * 删除菜品
     *
     * @param id 菜品id
     * @return 成功或失败信息
     */
    Result delete(Long id);

    /**
     * 根据食物类别进行分页查询
     *
     * @param pageInfoDTO 分页参数
     * @return 分页结果
     */
    Result findFoodPageByTypeId(PageInfoDTO pageInfoDTO);

    /**
     * 根据菜品id获取菜品
     *
     * @param id 菜品id
     * @return 菜品信息
     */
    Result findInfo(Long id);
}
