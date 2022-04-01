package com.lt.health.controller;

import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Motion;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.service.MotionService;
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
    @PostMapping("/add")
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
    public Result delete(@PathVariable Long id) {
        return motionService.delete(id);
    }
}
