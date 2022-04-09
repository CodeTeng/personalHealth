package com.lt.health.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Food;
import com.lt.health.entity.FoodType;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.service.FoodService;
import com.lt.health.service.FoodTypeService;
import com.lt.health.utils.QiniuUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * @description: 食物控制器
 * @author: 狂小腾
 * @date: 2022/4/3 19:39
 */
@RestController
@RequestMapping("/food")
@Api(tags = "食物管理接口")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private QiniuUtil qiniuUtil;

    @Autowired
    private FoodTypeService foodTypeService;

    @ApiOperation("通过excel批量导入")
    @PostMapping("/batchImport")
    public Result batchImport(@RequestParam("file") MultipartFile file) {
        try {
            ImportParams params = new ImportParams();
            List<Food> list = ExcelImportUtil.importExcel(file.getInputStream(), Food.class, params);
            if (list == null) {
                return Result.fail(MessageConstant.FOOD_LIST_BATCH_FAIL);
            }
            list.forEach(item -> {
                String imageUrls = item.getImageUrls();
                if (StringUtils.isNotEmpty(imageUrls)) {
                    try {
                        FileInputStream inputStream = new FileInputStream(imageUrls);
                        String uuid = UUID.randomUUID().toString().substring(0, 7);
                        String suffix = imageUrls.substring(imageUrls.lastIndexOf("."));
                        String fileName = uuid + suffix;
                        // 返回上传图片名称
                        String upload = qiniuUtil.upload(inputStream, fileName);
                        item.setImageUrls(upload);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            return foodService.batchImport(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(MessageConstant.FOOD_LIST_BATCH_FAIL);
        }
    }

    @ApiOperation(value = "分页查询菜品分类及其菜品信息")
    @PostMapping("/type/findPage")
    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.PAGE_FAIL);
        }
        return foodService.findPage(pageInfoDTO);
    }

    @ApiOperation(value = "添加菜品分类")
    @PostMapping("/type/insert")
    public Result insertType(@RequestBody FoodType foodType) {
        return foodTypeService.insertType(foodType);
    }

    @ApiOperation(value = "修改菜品分类")
    @PostMapping("/type/update")
    public Result updateType(@RequestBody FoodType foodType) {
        return foodTypeService.updateType(foodType);
    }

    @ApiOperation(value = "删除菜品分类")
    @DeleteMapping("/type/delete/{id}")
    public Result deleteType(@PathVariable Long id) {
        return foodTypeService.deleteType(id);
    }

    @ApiOperation("查询所有的菜品分类")
    @GetMapping("/typeAll")
    public Result typeAll() {
        return foodTypeService.typeAll();
    }

    @ApiOperation("菜品分页查询")
    @PostMapping("/findPage")
    public Result findFoodPage(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.PAGE_FAIL);
        }
        return foodService.findFoodPage(pageInfoDTO);
    }

    @ApiOperation(value = "添加菜品")
    @PostMapping("/insert")
    public Result insert(@RequestBody Food food) {
        return foodService.insert(food);
    }

    @ApiOperation(value = "修改菜品")
    @PostMapping("/update")
    public Result update(@RequestBody Food food) {
        return foodService.update(food);
    }

    @ApiOperation(value = "删除菜品")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return foodService.delete(id);
    }

    @ApiOperation(value = "根据食物类别查询食物并分页")
    @PostMapping("/typeId")
    public Result findFoodPageByTypeId(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.PAGE_FAIL);
        }
        return foodService.findFoodPageByTypeId(pageInfoDTO);
    }

    @ApiOperation("根据菜品id获取食物")
    @GetMapping("/{id}")
    public Result foodInfo(@PathVariable Long id) {
        if (id == null) {
            return Result.fail("请传递菜品编号");
        }
        return foodService.findInfo(id);
    }
}
