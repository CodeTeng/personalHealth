package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.entity.Goods;
import com.lt.health.service.GoodsService;
import com.lt.health.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author 狂小腾
* @description 针对表【sys_goods】的数据库操作Service实现
* @createDate 2022-03-26 20:36:16
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

}




