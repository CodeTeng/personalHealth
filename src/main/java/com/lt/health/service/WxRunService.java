package com.lt.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.constant.Result;
import com.lt.health.entity.WxRun;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_wx_run(微信运动步数记录表)】的数据库操作Service
 * @createDate 2022-03-26 20:36:16
 */
public interface WxRunService extends IService<WxRun> {

    /**
     * 获取WX运动步数接口
     *
     * @param runs 实时WX步数
     * @return WX步数和提示信息
     */
    Result getRunStep(List<WxRun> runs);

    /**
     * WX步数记录接口
     *
     * @return WX步数记录
     */
    Result stepReport();
}
