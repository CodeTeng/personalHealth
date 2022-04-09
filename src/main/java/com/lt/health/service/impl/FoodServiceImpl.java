package com.lt.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Food;
import com.lt.health.entity.FoodType;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.mapper.FoodMapper;
import com.lt.health.mapper.FoodTypeMapper;
import com.lt.health.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_food(食物详情表)】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food>
        implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private FoodTypeMapper foodTypeMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Result batchImport(List<Food> list) {
        log.info("开始读取食物列表");
        List<Food> foods = new ArrayList<>();
        for (Food food : list) {
            log.info("查询食物的分类");
            FoodType foodType = foodTypeMapper.selectById(food.getTypeId());
            log.info("根据菜品名称查询食物");
            Food findFood = foodMapper.findByTitle(food.getTitle());
            if (findFood != null) {
                // 根据导入的食物更新
                foodMapper.updateById(food);
            } else {
                // 添加
                food.setTypeId(foodType.getId());
                foods.add(food);
            }
        }
        foodMapper.insertList(foods);
        return Result.success(MessageConstant.BATCH_IMPORT_SUCCESS);
    }

    @Override
    public Result findPage(PageInfoDTO pageInfoDTO) {
        log.info("开始分页");
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        Page<FoodType> page = new Page<>(pageNumber, pageSize);
        List<FoodType> list = foodTypeMapper.findPage(queryString);
        page.setRecords(list);
        page.setTotal(list.size());
        return Result.success(MessageConstant.PAGE_SUCCESS, page);
    }

    @Override
    public Result findFoodPage(PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        Page<Food> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(queryString), "title", queryString);
        Page<Food> foodPage = foodMapper.selectPage(page, queryWrapper);
        long total = foodPage.getTotal();
        log.info("分页条数-->{}", total);
        return Result.success(MessageConstant.PAGE_SUCCESS, foodPage);
    }

    @Transactional
    @Override
    public Result insert(Food food) {
        log.info("添加菜品");
        int flag = foodMapper.insert(food);
        if (flag == 0) {
            return Result.fail(MessageConstant.FOOD_INSERT_FAIL);
        }
        return Result.success(MessageConstant.FOOD_INSERT_SUCCESS);
    }

    @Transactional
    @Override
    public Result update(Food food) {
        log.info("更新菜品");
        int flag = foodMapper.updateById(food);
        if (flag == 0) {
            return Result.fail(MessageConstant.FOOD_UPDATE_FAIL);
        }
        return Result.success(MessageConstant.FOOD_UPDATE_SUCCESS);
    }

    @Override
    public Result delete(Long id) {
        log.info("删除菜品");
        int flag = foodMapper.deleteById(id);
        if (flag == 0) {
            return Result.fail(MessageConstant.FOOD_DELETE_FAIL);
        }
        return Result.success(MessageConstant.FOOD_DELETE_SUCCESS);
    }

    @Override
    public Result findFoodPageByTypeId(PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        Page<Food> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", queryString);
        Page<Food> foodPage = foodMapper.selectPage(page, queryWrapper);
        long total = foodPage.getTotal();
        log.info("分页条数-->{}", total);
        return Result.success(MessageConstant.PAGE_SUCCESS, foodPage);
    }

    @Override
    public Result findInfo(Long id) {
        log.info("获取单个菜品信息");
        Food food = foodMapper.selectById(id);
        if (food == null) {
            return Result.fail(MessageConstant.FOOD_SELECT_FAIL);
        }
        return Result.success(MessageConstant.FOOD_SELECT_SUCCESS, food);
    }
}




