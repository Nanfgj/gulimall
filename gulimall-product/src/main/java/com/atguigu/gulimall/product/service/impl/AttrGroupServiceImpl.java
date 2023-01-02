package com.atguigu.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.AttrGroupDao;
import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    /**
     *
     * @param params 前端分页数据
     * @param catelogId 三级id
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer catelogId) {
        //获取检索关键字
        String key = (String) params.get("key");
        LambdaQueryWrapper<AttrGroupEntity> lqw = new LambdaQueryWrapper<>();

        //模糊查询关键字
        if (!StringUtils.isEmpty(key)){
            lqw.and((obj)->{
                obj.like(AttrGroupEntity::getAttrGroupName,key);
            });
        }
        //catelogId == 0  查询全部列表
        if (catelogId == 0){
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    lqw
            );
            return new PageUtils(page);
        }else {
            lqw.eq(AttrGroupEntity::getCatelogId,catelogId);
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    lqw);
            return new PageUtils(page);
        }
    }

}