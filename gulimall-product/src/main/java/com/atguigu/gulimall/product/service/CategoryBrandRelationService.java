package com.atguigu.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author changlu
 * @email 939974883@qq.com
 * @date 2022-11-05 16:20:08
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //保存品牌和分类关联关系
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    //同步其它关联表中的数据
    void updateBrand(Long brandId, String name);

    void updateCategory(Long catId, String name);
}

