package com.lt.health.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 商品控制器
 * @author: 狂小腾
 * @date: 2022/3/22 8:34
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

//    @Autowired
//    private GoodsService goodsService;
//
//    /**
//     * 根据用户名分页查询
//     *
//     * @param pageInfoDTO 分页参数
//     * @return 结果集
//     */
//    @PostMapping("/findPage")
//    public Result findPage(@RequestBody PageInfoDTO pageInfoDTO) {
//        String queryInfo = pageInfoDTO.getQueryInfo();
//        Integer pageNum = pageInfoDTO.getPageNum();
//        Integer pageSize = pageInfoDTO.getPageSize();
//        if (StringUtils.isAnyBlank(queryInfo, String.valueOf(pageNum), String.valueOf(pageSize))) {
//            return Result.fail(400, MessageConstant.GOODS_SELECT_FAIL);
//        }
//        return goodsService.findPage(pageInfoDTO);
//    }
//
//    /**
//     * 通过excel导入添加商品信息
//     *
//     * @param file   从浏览器上传的Excel文档
//     * @param userId 用户id
//     * @return 结果集
//     */
//    @PostMapping("/batchImport")
//    public Result batchImport(@RequestParam("goods") MultipartFile file, @RequestParam("userId") Integer userId) {
//        if (userId <= 0) {
//            return Result.fail(400, MessageConstant.BATCH_IMPORT_FAIL);
//        }
//        if (file == null || file.getSize() == 0) {
//            return Result.fail(400, MessageConstant.BATCH_IMPORT_FAIL);
//        }
//        try {
//            List<Goods> list = EasyExcelUtil.readExcel(file.getInputStream(), Goods.class);
//            return goodsService.batchImport(list, userId);
//        } catch (Exception e) {
//            return new Result(400, MessageConstant.FILE_READER_FAIL);
//        }
//    }
//
//    /**
//     * Excel导出所有的商品信息
//     *
//     * @param response 响应给客户端的输出流(用于输出excel文档)
//     * @throws IOException IOException
//     */
//    @GetMapping("/batchExport")
//    public void batchExport(HttpServletResponse response) throws IOException {
//        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("name", "price", "number", "create_time", "model_type"
//                , "control_mode", "main_function", "wifi_function", "battery",
//                "characteristic", "size", "other");
//        List<Goods> list = goodsService.list(queryWrapper);
//        // 代表的Excel文件类型
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
//        // 这里需要设置不关闭流
//        ServletOutputStream outputStream = response.getOutputStream();
//        EasyExcel.write(outputStream)
//                .excelType(ExcelTypeEnum.XLSX)
//                .sheet("商品列表")
//                .head(Goods.class)
//                .doWrite(list);
//        outputStream.flush();
//        outputStream.close();
//    }
}
