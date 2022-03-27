package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.entity.FoodType;
import com.lt.health.service.FoodTypeService;
import com.lt.health.mapper.FoodTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author 狂小腾
* @description 针对表【sys_food_type(食物分类表)】的数据库操作Service实现
* @createDate 2022-03-26 20:36:16
*/
@Service
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeMapper, FoodType>
    implements FoodTypeService {

}




