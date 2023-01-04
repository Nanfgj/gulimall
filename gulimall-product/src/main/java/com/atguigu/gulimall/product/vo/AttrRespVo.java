package com.atguigu.gulimall.product.vo;

import lombok.Data;

/**
 * @author nanfgj
 * @create 2023-01-03 22:25
 */
@Data
public class AttrRespVo extends AttrVo{
    private String catelogName;
    private String groupName;

    private Long[] catelogPath;
}
