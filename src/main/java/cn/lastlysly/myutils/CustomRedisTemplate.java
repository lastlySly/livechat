package cn.lastlysly.myutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-29 18:33
 * StringRedisTemplate的操作实现类
 **/
@Component
public class CustomRedisTemplate {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 保存
     * @param key 键
     * @param val 值
     */
    public void redisSave(String key,String val){
        stringRedisTemplate.opsForValue().set(key,val);
    }

    /**
     * 设置保存并缓存时间
     * @param key 键
     * @param val 值
     * @param timeout 缓存时间
     */
    public void redisSave(String key,String val,long timeout){
        stringRedisTemplate.opsForValue().set(key,val,timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public String redisGet(String key){
        String val = stringRedisTemplate.opsForValue().get(key);
        return val;
    }

    /**
     * 根据key模糊查询
     * @param key
     * @return
     */
    public Set<String> redisGetValueByKeys(String key){
        //stringRedisTemplate.keys模糊查询
        Set<String> valSet = stringRedisTemplate.keys(key);

        return valSet;
    }

}
