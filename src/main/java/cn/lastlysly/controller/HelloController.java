package cn.lastlysly.controller;

import cn.lastlysly.myutils.MyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-25 14:53
 **/
@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*注入StringRedisTemplate*/
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!!!你好!";
    }

    @GetMapping("/redistest")
    public String redisTest(){
       stringRedisTemplate.opsForValue().set("name","lastly");
       String getStr = stringRedisTemplate.opsForValue().get("name");
        logger.info("测试logback的整合:{}",getStr);
        logger.info("测试占位符:{}测试logback的整合<ceshi>",getStr);
        logger.info("测试占位符:{}测试logback的整合<>",getStr);
       return getStr;
    }


}
