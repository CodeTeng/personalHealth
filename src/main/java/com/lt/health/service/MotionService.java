package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.Motion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.entity.dto.PageInfoDTO;

/**
 * @author 狂小腾
 * @description 针对表【sys_motion(运动表)】的数据库操作Service
 * @createDate 2022-03-26 20:36:16
 */
public interface MotionService extends IService<Motion> {

    /**
     * 查询单个运动项目
     *
     * @param id 运动项目id
     * @return 该运动项目信息
     */
    Result findInfo(Long id);

    /**
     * 分页查询
     *
     * @param pageInfoDTO 分页参数---根据运动项目名称
     * @return 分页结果集
     */
    Result findPage(PageInfoDTO pageInfoDTO);

    /**
     * 增加运动项目
     *
     * @param sport 运动项目
     * @return 成功或失败信息
     */
    Result add(Motion sport);

    /**
     * 修改运动项目名称
     *
     * @param sport 运动项目
     * @return 成功或失败信息
     */
    Result edit(Motion sport);

    /**
     * 删除运动项目
     *
     * @param id 运动项目id
     * @return 成功或失败
     */
    Result delete(Long id);
}
