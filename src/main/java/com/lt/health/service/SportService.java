package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.Sport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.entity.dto.PageInfoDTO;

/**
 * @author 狂小腾
 * @description 针对表【sys_sport(运动咨询)】的数据库操作Service
 * @createDate 2022-03-26 20:36:16
 */
public interface SportService extends IService<Sport> {

    /**
     * 分页查询
     *
     * @param pageInfoDTO 分页参数条件---标题或者内容
     * @return 分页结果
     */
    Result findPage(PageInfoDTO pageInfoDTO);

    /**
     * 获取运动咨询详细信息
     *
     * @param id id
     * @return 详细信息
     */
    Result getInfo(Long id);

    /**
     * 添加运动咨询
     *
     * @param sport 添加运动咨询信息
     * @return 成功或者失败信息
     */
    Result insert(Sport sport);

    /**
     * 修改运动咨询信息
     *
     * @param sport 修改运动咨询信息
     * @return 成功或者失败信息
     */
    Result update(Sport sport);

    /**
     * 删除运动咨询信息
     *
     * @param id id
     * @return 删除提示信息
     */
    Result delete(Long id);
}
