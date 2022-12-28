package com.atguigu.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author changlu
 * @email 939974883@qq.com
 * @date 2022-11-05 16:20:08
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

     //1.查出分类的树形列表
    List<CategoryEntity> listWishTree();

    //删除分类菜单
    void removeMenuByIds(List<Long> asList);

}

