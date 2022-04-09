package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.FoodType;
import com.lt.health.service.FoodTypeService;
import com.lt.health.mapper.FoodTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_food_type(食物分类表)】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeMapper, FoodType>
        implements FoodTypeService {

    @Autowired
    private FoodTypeMapper foodTypeMapper;

    @Transactional
    @Override
    public Result insertType(FoodType foodType) {
        log.info("添加食物分类");
        Long id = foodType.getId();
        FoodType findFoodType = foodTypeMapper.selectById(id);
        if (findFoodType != null) {
            return Result.fail(MessageConstant.FOOD_TYPE_INSERT_FAIL);
        }
        foodTypeMapper.insert(foodType);
        return Result.success(MessageConstant.FOOD_TYPE_INSERT_SUCCESS);
    }

    @Transactional
    @Override
    public Result updateType(FoodType foodType) {
        log.info("更新食物分类");
        int flag = foodTypeMapper.updateById(foodType);
        if (flag == 0) {
            return Result.fail(MessageConstant.FOOD_TYPE_UPDATE_FAIL);
        }
        return Result.success(MessageConstant.FOOD_TYPE_UPDATE_SUCCESS);
    }

    @Override
    public Result deleteType(Long id) {
        log.info("删除食物分类");
        if (id < 0) {
            return Result.fail(MessageConstant.FOOD_TYPE_DELETE_FAIL);
        }
        int flag = foodTypeMapper.deleteById(id);
        if (flag == 0) {
            return Result.fail(MessageConstant.FOOD_TYPE_DELETE_FAIL);
        }
        return Result.success(MessageConstant.FOOD_TYPE_DELETE_SUCCESS);
    }

    @Override
    public Result typeAll() {
        List<FoodType> foodTypes = foodTypeMapper.selectList(null);
        return Result.success(MessageConstant.FOOD_TYPE_SELECT_SUCCESS, foodTypes);
    }
}




