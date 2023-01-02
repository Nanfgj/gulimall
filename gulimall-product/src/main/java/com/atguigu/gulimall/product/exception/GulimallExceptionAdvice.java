package com.atguigu.gulimall.product.exception;

import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nanfgj
 * @create 2023-01-02 14:47
 * 集中处理所有异常
 */
//@ControllerAdvice
//@ResponseBody
@Slf4j
@RestControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")
public class GulimallExceptionAdvice {

    @ExceptionHandler(value = Exception.class) //指定捕获异常类型
    public R handleValidException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        Map<String, String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item)->{
            map.put(item.getField(),item.getDefaultMessage());
        });
        log.error("数据检验出现问题{},异常类型{}",exception.getMessage(),exception.getClass());
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(),BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data",map);
    }

    //统一异常处理
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        log.error("未知异常{},异常类型{}",throwable.getMessage(),throwable.getClass());
        return R.error(BizCodeEnum.UNKNOW_EXEPTION.getCode(), BizCodeEnum.UNKNOW_EXEPTION.getMsg());
    }
}
