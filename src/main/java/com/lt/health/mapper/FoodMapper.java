package com.lt.health.mapper;

import com.lt.health.entity.Food;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_food(食物详情表)】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.Food
 */
@Repository
public interface FoodMapper extends BaseMapper<Food> {

    /**
     * 根据食物名称查找食物
     *
     * @param title 食物名称
     * @return 食物
     */
    @Select("select * from sys_food where title = #{title}")
    Food findByTitle(@Param("title") String title);

    /**
     * 插入食物列表
     *
     * @param foods 食物列表
     */
    void insertList(@Param("foods") List<Food> foods);
}




