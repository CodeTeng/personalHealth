package com.lt.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Sport;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.mapper.SportMapper;
import com.lt.health.service.SportService;
import com.lt.health.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_sport(运动咨询)】的数据库操作Service实现
 * @createDate 2022-03-26 20:36:16
 */
@Service
@Slf4j
public class SportServiceImpl extends ServiceImpl<SportMapper, Sport>
        implements SportService {

    @Autowired
    private SportMapper sportmapper;

    @Override
    public Result findPage(PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        String queryString = pageInfoDTO.getQueryString();
        log.info("开始数据分页-->页码{},--->页数{}--->查询内容{}", pageNumber, pageSize, queryString);
        Page<Sport> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Sport> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(queryString), "title", queryString)
                .or().like(StringUtils.isNotEmpty(queryString), "content", queryString);
        Page<Sport> sportPage = sportmapper.selectPage(page, queryWrapper);
        List<Sport> records = sportPage.getRecords();
        long total = sportPage.getTotal();
        log.info("查询的总条数-->{}", total);
        log.info("分页列表-->{}", records);
        return Result.success(MessageConstant.PAGE_SUCCESS, sportPage);
    }

    @Override
    public Result getInfo(Long id) {
        Sport sport = sportmapper.selectById(id);
        return Result.success(MessageConstant.SPORT_SELECT_SUCCESS, sport);
    }

    @Transactional
    @Override
    public Result insert(Sport sport) {
        sport.setCreateName(SecurityUtil.getUsername());
        sportmapper.insert(sport);
        return Result.success(MessageConstant.SPORT_CONSULT_INSERT_SUCCESS);
    }

    @Transactional
    @Override
    public Result update(Sport sport) {
        sport.setUpdateName(SecurityUtil.getUsername());
        sport.setUpdateTime(new Date());
        sportmapper.updateById(sport);
        return Result.success(MessageConstant.SPORT_CONSULT_UPDATE_SUCCESS);
    }

    @Override
    public Result delete(Long id) {
        sportmapper.deleteById(id);
        return Result.success(MessageConstant.SPORT_CONSULT_DELETE_SUCCESS);
    }
}




