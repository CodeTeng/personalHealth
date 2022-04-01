package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.entity.dto.PageInfoDTO;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_goods】的数据库操作Service
 * @createDate 2022-03-26 20:36:16
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 根据商品名称分页查询
     *
     * @param pageInfoDTO 分页参数
     * @return 分页结果集
     */
    Result findPage(PageInfoDTO pageInfoDTO);

    /**
     * 查询单个商品信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    Result findInfo(Long id);

    /**
     * 添加商品
     *
     * @param goods 商品信息
     * @return 成功或失败信息
     */
    Result insert(Goods goods);

    /**
     * 修改商品
     *
     * @param goods 商品信息
     * @return 成功或失败信息
     */
    Result update(Goods goods);

    /**
     * 删除商品
     *
     * @param id 商品id
     * @return 成功或失败信息
     */
    Result delete(Long id);

    /**
     * 通过excel导入添加商品信息
     *
     * @param list 商品信息数据
     * @return 结果集
     */
    Result batchImport(List<Goods> list);
}
