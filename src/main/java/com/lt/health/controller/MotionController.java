package com.lt.health.controller;

import com.lt.health.aop.log.SystemCrmLog;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.constant.TableNameConstant;
import com.lt.health.entity.Motion;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.service.MotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 运动项目控制器
 * @author: 狂小腾
 * @date: 2022/4/1 13:15
 */
@RestController
@RequestMapping("/motion")
@Api(tags = "运动项目接口")
public class MotionController {

    @Autowired
    private MotionService motionService;

    /**
     * 查询单个运动项目
     *
     * @param id 运动项目id
     * @return 该运动项目信息
     */
    @GetMapping("/{id}")
    @SystemCrmLog(description = "进行查看单个运动项目操作", tableName = {TableNameConstant.MOTION_TABLE_NAME})
    @ApiOperation("查询单个运动项目接口")
    public Result findInfo(@PathVariable Long id) {
        return motionService.findInfo(id);
    }

    /**
     * 分页查询
     *
     * @param pageInfoDTO 分页参数---根据运动项目名称
     * @return 分页结果集
     */
    @PostMapping("/findPage")
    @SystemCrmLog(description = "进行查看运动项目操作", tableName = {TableNameConstant.MOTION_TABLE_NAME})
    @ApiOperation("运动项目分页查询接口")
    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.PAGE_FAIL);
        }
        return motionService.findPage(pageInfoDTO);
    }

    /**
     * 增加运动项目
     *
     * @param sport 运动项目
     * @return 成功或失败信息
     */
    @SystemCrmLog(description = "进行增加运动项目操作", tableName = {TableNameConstant.MOTION_TABLE_NAME})
    @PostMapping("/add")
    @ApiOperation("增加运动项目接口")
    public Result add(@RequestBody Motion sport) {
        return motionService.add(sport);
    }

    /**
     * 修改运动项目名称
     *
     * @param sport 运动项目
     * @return 成功或失败信息
     */
    @PutMapping("/edit")
    @ApiOperation("修改运动项目接口")
    @SystemCrmLog(description = "进行修改运动项目操作", tableName = {TableNameConstant.MOTION_TABLE_NAME})
    public Result edit(@RequestBody Motion sport) {
        return motionService.edit(sport);
    }

    /**
     * 删除运动项目
     *
     * @param id 运动项目id
     * @return 成功或失败
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除运动项目接口")
    @SystemCrmLog(description = "进行删除运动项目操作", tableName = {TableNameConstant.MOTION_TABLE_NAME})
    public Result delete(@PathVariable Long id) {
        return motionService.delete(id);
    }
}
