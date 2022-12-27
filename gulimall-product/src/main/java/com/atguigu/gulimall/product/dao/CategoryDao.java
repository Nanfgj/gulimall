package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author changlu
 * @email 939974883@qq.com
 * @date 2022-11-05 16:20:08
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
