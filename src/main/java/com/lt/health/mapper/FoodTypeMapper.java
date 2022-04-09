package com.lt.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.health.entity.FoodType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_food_type(食物分类表)】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.FoodType
 */
@Repository
public interface FoodTypeMapper extends BaseMapper<FoodType> {

    /**
     * 分页查询 查询出食物类别和食物信息
     *
     * @param queryString 查询条件---菜单分类标题
     * @return 食物信息
     */
    List<FoodType> findPage(@Param("title") String queryString);
}




