package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.vo.AttrRespVo;
import com.atguigu.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author changlu
 * @email 939974883@qq.com
 * @date 2022-11-05 16:20:08
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //添加商品属性和对应分组
    void saveAttr(AttrVo attr);

    //获取分类规格参数
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    //获取规格参数的详细信息
    AttrRespVo getAttrInfo(Long attrId);
}

