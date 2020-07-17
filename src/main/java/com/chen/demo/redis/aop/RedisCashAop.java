package com.chen.demo.redis.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chen.demo.redis.entity.TDemo;
import com.chen.demo.redis.mapper.TDemoMapper;
import com.chen.demo.redis.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.sql.Array;
import java.util.Arrays;

/**
 * @author chen
 * @description
 * @date 2020/7/16
 */
@Aspect
@Component
@Slf4j
public class RedisCashAop {

    @Autowired
    private RedisUtil redisUtil;


    private final static String REDIS_KEY = "demo.mapper.key:";

    @Pointcut("@annotation(com.chen.demo.redis.annotation.RedisCash)")
    public void redisCash() {
    }

    @Around("redisCash()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        //获取方法，此处可将signature强转为MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object object = redisUtil.getObject(REDIS_KEY.concat((method.getName())));
        if(object!=null){
            return object;
        }
        try {
            object = joinPoint.proceed();
            redisUtil.setObject(REDIS_KEY.concat((method.getName())),object);
        } catch (Throwable throwable) {
            log.error("获取失败{}",throwable);
        }
        return object;
    }
}
