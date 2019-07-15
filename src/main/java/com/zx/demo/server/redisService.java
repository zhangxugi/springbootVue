package com.zx.demo.server;

import com.zx.demo.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;
@Service
@Transactional
public class redisService {

        @Autowired
        private StringRedisTemplate stringRedisTemplate;
        @Autowired
        private RedisTemplate <String,Object>redisTemplate;
        //放对象
        public void set(String key, Emp user) {
            redisTemplate.opsForValue().set(key, user);
        }
        //根据键获取
        public Emp get(String key) {
        return (Emp) redisTemplate.boundValueOps(key).get();
        }
        //放String值（这里做的是验证码，设置时间为60秒）
        public void setCode(String key, String code) {
            stringRedisTemplate.opsForValue().set(key, code, 60, TimeUnit.SECONDS);
        }
        //获取String类型
        public String getCode(String key) {
            return stringRedisTemplate.boundValueOps(key).get();
        }
//删除
//    public void deletredis(String id){
//        ValueOperations<String,String> vo = redisTemplate.opsForValue();
//        vo.getOperations().delete(id);
//    }

    }
