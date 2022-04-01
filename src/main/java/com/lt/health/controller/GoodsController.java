package com.lt.health.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.Goods;
import com.lt.health.entity.dto.PageInfoDTO;
import com.lt.health.service.GoodsService;
import com.lt.health.utils.EasyExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description: 商品控制器
 * @author: 狂小腾
 * @date: 2022/3/22 8:34
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 根据商品名称分页查询
     *
     * @param pageInfoDTO 分页参数
     * @return 分页结果集
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
        Integer pageNumber = pageInfoDTO.getPageNumber();
        Integer pageSize = pageInfoDTO.getPageSize();
        if (StringUtils.isAnyBlank(String.valueOf(pageNumber), String.valueOf(pageSize))) {
            return Result.fail(MessageConstant.PAGE_FAIL);
        }
        return goodsService.findPage(pageInfoDTO);
    }

    /**
     * 查询单个商品信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    @GetMapping("/{id}")
    public Result findInfo(@PathVariable Long id) {
        if (id == null || id < 0) {
            return Result.fail(MessageConstant.GOODS_SELECT_FAIL);
        }
        return goodsService.findInfo(id);
    }

    /**
     * 添加商品
     *
     * @param goods 商品信息
     * @return 成功或失败信息
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Goods goods) {
        return goodsService.insert(goods);
    }

    /**
     * 修改商品
     *
     * @param goods 商品信息
     * @return 成功或失败信息
     */
    @PutMapping("/update")
    public Result update(@RequestBody Goods goods) {
        return goodsService.update(goods);
    }

    /**
     * 删除商品
     *
     * @param id 商品id
     * @return 成功或失败信息
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        if (id < 0) {
            return Result.fail(MessageConstant.DELETE_GOODS_FAIL);
        }
        return goodsService.delete(id);
    }

    /**
     * 通过excel导入添加商品信息
     *
     * @param file   从浏览器上传的Excel文档
     * @return 结果集
     */
    @PostMapping("/batchImport")
    public Result batchImport(@RequestParam MultipartFile file) {
        if (file == null || file.getSize() == 0) {
            return Result.fail(MessageConstant.BATCH_IMPORT_FAIL);
        }
        try {
            List<Goods> list = EasyExcelUtil.readExcel(file.getInputStream(), Goods.class);
            return goodsService.batchImport(list);
        } catch (Exception e) {
            if (e.getCause() instanceof ExcelDataConvertException) {
                return Result.fail("表格类型错误，商品数量为整数，价格为两个小数点的数字");
            } else{
                return Result.fail("商品导入异常，请联系管理员");
            }
        }
    }

    /**
     * Excel导出所有的商品信息
     *
     * @param response 响应给客户端的输出流(用于输出excel文档)
     * @throws IOException IOException
     */
    @GetMapping("/batchExport")
    public void batchExport(HttpServletResponse response) throws IOException {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "price", "number", "create_time", "model_type"
                , "control_mode", "main_function", "wifi_function", "battery",
                "characteristic", "size", "other");
        List<Goods> list = goodsService.list(queryWrapper);
        // 代表的Excel文件类型
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
        // 这里需要设置不关闭流
        ServletOutputStream outputStream = response.getOutputStream();
        EasyExcel.write(outputStream)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("商品列表")
                .head(Goods.class)
                .doWrite(list);
        outputStream.flush();
        outputStream.close();
    }
}
