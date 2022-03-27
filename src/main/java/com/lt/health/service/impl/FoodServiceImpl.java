package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.entity.Food;
import com.lt.health.service.FoodService;
import com.lt.health.mapper.FoodMapper;
import org.springframework.stereotype.Service;

/**
* @author 狂小腾
* @description 针对表【sys_food(食物详情表)】的数据库操作Service实现
* @createDate 2022-03-26 20:36:16
*/
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food>
    implements FoodService {

}




