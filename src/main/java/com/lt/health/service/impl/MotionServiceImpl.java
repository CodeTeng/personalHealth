package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.entity.Motion;
import com.lt.health.service.MotionService;
import com.lt.health.mapper.MotionMapper;
import org.springframework.stereotype.Service;

/**
* @author 狂小腾
* @description 针对表【sys_motion(运动表)】的数据库操作Service实现
* @createDate 2022-03-26 20:36:16
*/
@Service
public class MotionServiceImpl extends ServiceImpl<MotionMapper, Motion>
    implements MotionService {

}




