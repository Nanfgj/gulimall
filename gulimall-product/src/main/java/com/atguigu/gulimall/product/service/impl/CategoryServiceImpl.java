package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

     //1.查出分类的树形列表
    @Override
    public List<CategoryEntity> listWishTree() {

        List<CategoryEntity> categoryEntityList = this.baseMapper.selectList(null);

        //找出一级分类
//        List<CategoryEntity> oneList = new ArrayList<>();
//        for (CategoryEntity categoryEntity : categoryEntityList) {
//            if (categoryEntity.getParentCid() == 0){
//                oneList.add(categoryEntity);
//            }
//        }
        //找出其他子id...

        //stream流方式：
        //2、组装成父子的树型结构
        List<CategoryEntity> ans = categoryEntityList.stream()
                .filter((menu) -> menu.getParentCid() == 0)
                .map((menu) -> {
                    menu.setChildren(getChildren(menu, categoryEntityList));
                    return menu;
                })
                .sorted((menu1, menu2) -> {
                    if (menu1.getSort() == null || menu2.getSort() == null) return 0;
                    return menu1.getSort() - menu2.getSort();
                })
                .collect(Collectors.toList());


        return ans;
    }

    //2.根据CatelogId，查询出完整三级分类
    @Override
    public Long[] findCatelogPath(Long catelogId) {

        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId,paths);

        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[parentPath.size()]);


        //3级分类id catelogId  --nanfgj
//        CategoryEntity categoryThree = this.baseMapper.selectById(catelogId);
//
//        Long catIdTwo = categoryThree.getParentCid();
//        CategoryEntity categoryTwo = this.baseMapper.selectById(catIdTwo);
//        Long catIdOne = categoryTwo.getParentCid();
//        return new Long[]{catIdOne,catIdTwo,catelogId};
    }

    //级联更新所有关联的数据
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());

    }

    //递归收集节点
    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        //收集当前节点
        paths.add(catelogId);
        CategoryEntity category = this.getById(catelogId);
        if (category.getParentCid() != 0){
            findParentPath(category.getParentCid(),paths);
        }
        return paths;
    }


    //删除分类菜单
    @Override
    public void removeMenuByIds(List<Long> asList) {
        this.baseMapper.deleteBatchIds(asList);
    }


    /**
     * 递归处理获取子分类
     * @param parent 父分类
     * @param all 所有分类
     * @return 已经获取到子分类的分类
     */
    public List<CategoryEntity> getChildren(CategoryEntity parent, List<CategoryEntity> all) {
        List<CategoryEntity> ans = all.stream()
                .filter((menu) -> menu.getParentCid().equals(parent.getCatId())) //Long类型比较需要进行equals
                .map((menu) -> {
                    menu.setChildren(getChildren(menu, all));
                    return menu;
                })
                .sorted((menu1, menu2) -> {
                    if (menu1.getSort() == null || menu2.getSort() == null) return 0;
                    return menu1.getSort() - menu2.getSort();
                })
                .collect(Collectors.toList());

        return ans;
    }

}