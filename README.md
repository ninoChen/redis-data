# redis缓存数据库数据示例

## RedisCash
#### 查询mapper方法加上注解，查询的时候会先从redis拿，假如null就从数据库获取并存入redis

## RedisUpdate
### 更新的mapper方法加上注解，更新是会先删除redis数据保证redis-数据库双写一致# redis-data
