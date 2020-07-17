package com.chen.demo.redis.controller;

import com.chen.demo.redis.entity.TDemo;
import com.chen.demo.redis.mapper.TDemoMapper;
import com.chen.demo.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author chen
 * @description
 * @date 2020/7/16
 */
@Slf4j
@RestController
@RequestMapping("/demo/redis")
public class DemoController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private TDemoMapper tDemoMapper;

    @GetMapping("test-redis")
    public String testRedis() {
        boolean redis =  redisUtil.set("test:redis","11",60);
        return String.valueOf(redis);
    }

    /**
     * 读取数据先从缓存拿
     * @return
     */
    @GetMapping("test-mapper")
    public String testMapper() {
        Object tDemo =  tDemoMapper.getDemoById("1");
        return tDemo.toString();
    }

    @GetMapping("test-update")
    public void updateMapper(){
        tDemoMapper.updateDemo(1, "toby");
    }

}
