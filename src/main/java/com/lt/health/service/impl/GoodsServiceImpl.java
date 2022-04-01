package com.lt.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Goods;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.mapper.GoodsMapper;
import com.lt.health.service.GoodsService;
import com.lt.health.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_goods】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
        implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Result findPage(PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        log.info("开始数据分页-->页码{}, --->{}页数--->查询内容{}", pageNumber, pageSize, queryString);
        Page<Goods> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Goods> query = new QueryWrapper<>();
        query.like(StringUtils.isNotEmpty(queryString), "name", queryString);
        Page<Goods> goodsPage = goodsMapper.selectPage(page, query);
        long total = goodsPage.getTotal();
        List<Goods> records = goodsPage.getRecords();
        log.info("查询的总条数-->{}", total);
        log.info("分页列表-->{}", records);
        return Result.success(MessageConstant.PAGE_SUCCESS, goodsPage);
    }

    @Override
    public Result findInfo(Long id) {
        log.info("获取单个商品信息");
        Goods goods = goodsMapper.selectById(id);
        if (goods == null) {
            return Result.fail(MessageConstant.GOODS_SELECT_FAIL);
        }
        return Result.success(MessageConstant.GOODS_SELECT_SUCCESS, goods);
    }


    @Transactional
    @Override
    public Result insert(Goods goods) {
        log.info("插入商品");
        Long id = goods.getId();
        Goods findGoods = goodsMapper.selectById(id);
        if (findGoods != null) {
            Result.fail(MessageConstant.GOODS_EXIST);
        }
        int flag = goodsMapper.insert(goods);
        if (flag <= 0) {
            return Result.fail(MessageConstant.ADD_GOODS_FAIL);
        }
        return Result.success(MessageConstant.ADD_GOODS_SUCCESS);
    }

    @Transactional
    @Override
    public Result update(Goods goods) {
        log.info("修改商品信息");
        int flag = goodsMapper.updateById(goods);
        if (flag < 1) {
            return Result.fail(MessageConstant.UPDATE_GOODS_FAIL);
        }
        return Result.success(MessageConstant.UPDATE_GOODS_SUCCESS);
    }

    @Override
    public Result delete(Long id) {
        log.info("删除商品");
        int flag = goodsMapper.deleteById(id);
        if (flag < 1) {
            return Result.fail(MessageConstant.DELETE_GOODS_FAIL);
        }
        return Result.fail(MessageConstant.DELETE_GOODS_SUCCESS);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result batchImport(List<Goods> list) {
        log.info("批量导入数据");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i = 0, j = 0;
        for (Goods goods : list) {
            goods.setCreateUserId(SecurityUtil.getUserId());
            Goods findGoods = goodsMapper.selectById(goods.getId());
            if (findGoods != null) {
                // 更新商品
                goods.setId(findGoods.getId());
                goods.setRemark("管理员：" + SecurityUtil.getUsername() + "于" + format.format(new Date()) + " --> 更新了商品:" + goods.getName() + "；目前数量：" + goods.getNumber());
                i += goodsMapper.updateById(goods);
            } else {
                // 添加商品
                goods.setCreateTime(new Date());
                goods.setRemark("管理员：" + SecurityUtil.getUsername() + "于" + format.format(new Date()) + "将商品：" + goods.getName() + "加入库存，数量：" + goods.getNumber());
                j += goodsMapper.insert(goods);
            }
        }
        if (i <= 0 && j <= 0) {
            return Result.fail(MessageConstant.BATCH_IMPORT_FAIL);
        }
        return Result.success(MessageConstant.BATCH_IMPORT_SUCCESS);
    }
}




