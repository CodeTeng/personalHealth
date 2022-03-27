package com.lt.health.mapper;

import com.lt.health.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_menu】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.Menu
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {


    /**
     * 根据用户ID查询菜单信息
     *
     * @param userId 用户id
     * @return 菜单信息
     */
    List<Menu> findMenusByUserId(@Param("userId") Long userId);

    /**
     * 根据父级id和用户id查询子级菜单
     *
     * @param parentId 父级id
     * @param userId   用户id
     * @return 子级菜单
     */
    List<Menu> findChildrenMenuByPidAndUserId(@Param("parentId") Long parentId, @Param("userId") Long userId);
}




