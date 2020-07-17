package com.chen.demo.redis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chen
 * @description
 * @date 2020/7/16
 */
@Data
@Accessors(chain = true)
public class TDemo implements Serializable{

    private String id;

    private String name;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
