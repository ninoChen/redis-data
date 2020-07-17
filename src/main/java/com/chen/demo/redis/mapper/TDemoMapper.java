package com.chen.demo.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.demo.redis.annotation.RedisCash;
import com.chen.demo.redis.annotation.RedisUpdate;
import com.chen.demo.redis.entity.TDemo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TDemoMapper extends BaseMapper<TDemo> {


    @RedisCash("getDemoByName")
    Object getDemoByName(@Param("name") String name);

    @RedisCash("getDemoById")
    TDemo getDemoById(@Param("id")String id);

    @RedisUpdate("getDemoById")
    void updateDemo(@Param("id") Integer id,@Param("name") String name);
}
