package com.zx.demo.Config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
    @EnableCaching
    public class RedisConfig {

        @Bean
        public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
            RedisCacheManager cacheManager = RedisCacheManager.create(connectionFactory);
            return cacheManager;
        }
//前者默认jdk序列方式   可以用来保存对象
        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
            redisTemplate.setConnectionFactory(factory);
            // 为 key 设置序列化器
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            // 为 value 设置序列化器
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            return redisTemplate;
        }
    //string的序列化方式，后者一般专门用于存储string格式
        @Bean
        public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
            StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
            stringRedisTemplate.setConnectionFactory(factory);
            return stringRedisTemplate;
        }

}