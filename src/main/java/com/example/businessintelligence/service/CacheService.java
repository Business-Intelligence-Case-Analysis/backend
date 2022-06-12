package com.example.businessintelligence.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author JiongJiongStrive
 * @version 1.0.0
 * @ClassName CacheService.java
 * @Description TODO
 * @createTime 2022年05月24日 21:18:00
 */
@Slf4j
@Service
public class CacheService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    public <K,V> void add(K key,V value){
        try{
            String str_key = String.valueOf(key);
            if(value!=null){
                redisTemplate.opsForValue()
                        .set(str_key, JSON.toJSONString(value));
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException("数据缓存至redis失败");
        }
    }

    public <K,V> void add(K key, V value, long time_out, TimeUnit unit){
        try{
            String str_key = String.valueOf(key);
            if(value!=null){
                String stringValue = JSON.toJSONString(value);
                redisTemplate.opsForValue()
                        .set(str_key, stringValue,time_out,unit);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException("数据缓存至redis失败");
        }
    }

    public <K,SK,V> void addHashCache(K key,SK subKey,V value){
        try{
            String str_key = String.valueOf(key);
            if(value!=null){
                redisTemplate.opsForHash().put(str_key,subKey,value);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException("数据缓存至redis失败");
        }
    }

    public <K,SK,V> void addHashCache(K key,SK subKey,V value,long time_out,TimeUnit unit){
        try{
            String str_key = String.valueOf(key);
            if(value!=null){
                redisTemplate.opsForHash().put(str_key,subKey,value);
                redisTemplate.expire(str_key,time_out,unit);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException("数据缓存至redis失败");
        }
    }

    public <K,V> void addListCache(K key,V value){
        try{
            String str_key = String.valueOf(key);
            if(value!=null){
                redisTemplate.opsForList().rightPush(str_key,JSON.toJSONString(value));
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException("数据缓存至redis失败");
        }
    }


    public <K,V> void addListCache(K key,V value,long time_out,TimeUnit unit){
        try{
            String str_key = String.valueOf(key);
            if(value!=null){
                String stringList = JSON.toJSONString(value);
                redisTemplate.opsForList().rightPush(str_key,stringList);
                redisTemplate.expire(str_key,time_out,unit);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException("数据缓存至redis失败");
        }
    }

    public <K> String get(K key){
        String value;
        try{
            value = redisTemplate.opsForValue().get(key);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("从redis缓存中获取缓存数据失败");
        }
        return value;
    }

    public <K,V> V getObject(K key,Class<V> clazz){
        String value = this.get(key);
        V result = null;
        if(!StringUtils.isEmpty(value)){
            result = JSON.parseObject(value,clazz);
        }
        return result;
    }

    public <K,V> List<V> getList(K key,Class<V> clazz){
        if(key instanceof String){
            String stringKey = (String) key;
            String value = redisTemplate.opsForList().index(stringKey,-1);
            List<V> result = Collections.emptyList();
            if(!StringUtils.isEmpty(value)){
                result=JSON.parseArray(value,clazz);
            }
            return result;
        }else{
            throw new RuntimeException("key type is not string");
        }
    }

    public <K,SK,V> V getHashValue(K key,SK sub_key,Class<V> clazz){
        V value=null;
        try{
            String clazzName = clazz.getName();
            String str_key = String.valueOf(key);
            value = (V)redisTemplate.opsForHash().get(str_key, sub_key);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("从redis缓存中获取缓存数据失败");
        }
        return value;
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public void delete(Collection<String> keys){
        redisTemplate.delete(keys);
    }

    public byte[] dump(String key){
        return redisTemplate.dump(key);
    }

    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 设置过期时间
     */
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }


    /**
     * 移除 key 的过期时间，key 将持久保持
     */
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 返回 key 的剩余的过期时间
     */
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 返回 key 的剩余的过期时间
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

}
