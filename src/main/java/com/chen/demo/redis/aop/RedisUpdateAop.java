package com.chen.demo.redis.aop;
import com.chen.demo.redis.annotation.RedisUpdate;
import com.chen.demo.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author chen
 * @description
 * @date 2020/7/17
 */
@Aspect
@Component
@Slf4j
public class RedisUpdateAop {

    @Autowired
    private RedisUtil redisUtil;

    private final static String REDIS_KEY = "demo.mapper.key:";

    @Pointcut("@annotation(com.chen.demo.redis.annotation.RedisUpdate)")
    public void redisUpdate(){
    }

    @Around("redisUpdate()&&@annotation(redisUe)")
    public void around(ProceedingJoinPoint joinPoint,RedisUpdate redisUe) {
        //执行更新方法前，删掉redis数据，保证书写一致
        redisUtil.del(REDIS_KEY.concat(redisUe.value()));
        try {
            //执行完成目标方法
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
