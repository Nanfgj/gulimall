package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author changlu
 * @email 939974883@qq.com
 * @date 2022-11-05 18:03:19
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
