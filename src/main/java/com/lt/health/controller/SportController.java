package com.lt.health.controller;

import com.lt.health.aop.log.SystemCrmLog;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.constant.TableNameConstant;
import com.lt.health.entity.Sport;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.service.SportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 运动咨询控制器
 * @author: 狂小腾
 * @date: 2022/3/31 15:40
 */
@RestController
@RequestMapping("/sport")
public class SportController {

    @Autowired
    private SportService sportService;

    /**
     * 分页查询
     *
     * @param pageInfoDTO 分页参数条件---标题或者内容
     * @return 分页结果
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.PAGE_FAIL);
        }
        return sportService.findPage(pageInfoDTO);
    }

    /**
     * 获取运动咨询详细信息
     *
     * @param id id
     * @return 详细信息
     */
    @GetMapping("/{id}")
    @SystemCrmLog(description = "进行获取运动咨询操作", tableName = TableNameConstant.SPORT_TABLE_NAME)
    public Result getInfo(@PathVariable Long id) {
        return sportService.getInfo(id);
    }

    /**
     * 添加运动咨询
     *
     * @param sport 添加运动咨询信息
     * @return 成功或者失败信息
     */
    @PostMapping("/insert")
    @SystemCrmLog(description = "进行添加运动咨询操作", tableName = TableNameConstant.SPORT_TABLE_NAME)
    public Result insert(@RequestBody Sport sport) {
        return sportService.insert(sport);
    }

    /**
     * 修改运动咨询信息
     *
     * @param sport 修改运动咨询信息
     * @return 成功或者失败信息
     */
    @PutMapping("/update")
    @SystemCrmLog(description = "进行更新运动咨询操作", tableName = TableNameConstant.SPORT_TABLE_NAME)
    public Result update(@RequestBody Sport sport) {
        return sportService.update(sport);
    }


    /**
     * 删除运动咨询信息
     *
     * @param id id
     * @return 删除提示信息
     */
    @DeleteMapping("/delete/{id}")
    @SystemCrmLog(description = "进行删除运动咨询操作", tableName = TableNameConstant.SPORT_TABLE_NAME)
    public Result delete(@PathVariable("id") Long id) {
        return sportService.delete(id);
    }
}
