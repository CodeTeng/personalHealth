package com.lt.health.mapper;

import com.lt.health.entity.WxRun;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_wx_run(微信运动步数记录表)】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.WxRun
 */
@Repository
public interface WxRunMapper extends BaseMapper<WxRun> {

    /**
     * 根据时间和openid查找WX运动
     *
     * @param time   时间
     * @param openid openid
     * @return 微信运动
     */
    @Select("select * from sys_wx_run where openid = #{openid} and time = #{time}")
    WxRun findStepByTimeAndOpenid(@Param("time") String time, @Param("openid") String openid);

    /**
     * 更新WX步数
     *
     * @param time   时间
     * @param openid openid
     * @param step   步数
     */
    @Update("update sys_wx_run set step = #{step} where openid = #{openid} and time = #{time}")
    void updateStep(@Param("time") String time, @Param("openid") String openid, @Param("step") Integer step);

    /**
     * 插入WX步数集合
     *
     * @param wxRuns WX步数
     */
    void insertStep(@Param("wxRuns") List<WxRun> wxRuns);

    /**
     * 查询前一周的WX步数
     *
     * @param dateTime 当前时间
     * @param endTime  前一周时间
     * @param openId   openid
     * @return 一周的WX步数
     */
    @Select("select * from sys_wx_run where openid = #{openid} and (time <= #{beginTime} and time >= #{endTime})")
    List<WxRun> findByTime(@Param("beginTime") String dateTime, @Param("endTime") String endTime, @Param("openid") String openId);
}




