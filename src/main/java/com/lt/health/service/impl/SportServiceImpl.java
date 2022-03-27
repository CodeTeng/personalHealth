package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.entity.Sport;
import com.lt.health.service.SportService;
import com.lt.health.mapper.SportMapper;
import org.springframework.stereotype.Service;

/**
* @author 狂小腾
* @description 针对表【sys_sport(运动咨询)】的数据库操作Service实现
* @createDate 2022-03-26 20:36:16
*/
@Service
public class SportServiceImpl extends ServiceImpl<SportMapper, Sport>
    implements SportService {

}




