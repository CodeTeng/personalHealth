package com.lt.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Motion;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.mapper.MotionMapper;
import com.lt.health.service.MotionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_motion(运动表)】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class MotionServiceImpl extends ServiceImpl<MotionMapper, Motion> implements MotionService {

    @Autowired
    private MotionMapper motionMapper;

    @Override
    public Result findInfo(Long id) {
        log.info("获取单个运动项目信息");
        Motion motion = motionMapper.selectById(id);
        if (motion == null) {
            return Result.fail(MessageConstant.MOTION_SELECT_FAIL);
        }
        return Result.success(MessageConstant.MOTION_SELECT_SUCCESS, motion);
    }

    @Override
    public Result findPage(PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        log.info("开始数据分页-->页码{}, --->{}页数--->查询内容{}", pageNumber, pageSize, queryString);
        Page<Motion> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Motion> query = new QueryWrapper<>();
        query.like(StringUtils.isNotEmpty(queryString), "name", queryString).
                or().like(StringUtils.isNotEmpty(queryString), "beneficial_position", queryString);
        Page<Motion> motionPage = motionMapper.selectPage(page, query);
        long total = motionPage.getTotal();
        List<Motion> records = motionPage.getRecords();
        log.info("查询的总条数-->{}", total);
        log.info("分页列表-->{}", records);
        return Result.success(MessageConstant.PAGE_SUCCESS, motionPage);
    }

    @Transactional
    @Override
    public Result add(Motion sport) {
        log.info("添加运动项目");
        Integer id = sport.getId();
        Motion motion = motionMapper.selectById(id);
        if (motion != null) {
            return Result.fail(MessageConstant.MOTION_INSERT_TIP);
        }
        int flag = motionMapper.insert(sport);
        if (flag <= 0) {
            return Result.fail(MessageConstant.MOTION_INSERT_FAIL);
        }
        return Result.success(MessageConstant.MOTION_INSERT_SUCCESS);
    }

    @Transactional
    @Override
    public Result edit(Motion sport) {
        log.info("修改运动项目");
        int flag = motionMapper.updateById(sport);
        if (flag <= 0) {
            return Result.fail(MessageConstant.MOTION_UPDATE_FAIL);
        }
        return Result.success(MessageConstant.MOTION_UPDATE_SUCCESS);
    }

    @Override
    public Result delete(Long id) {
        log.info("删除运动项目");
        int flag = motionMapper.deleteById(id);
        if (flag <= 0) {
            return Result.fail(MessageConstant.MOTION_DELETE_FAIL);
        }
        return Result.success(MessageConstant.MOTION_DELETE_SUCCESS);
    }
}




