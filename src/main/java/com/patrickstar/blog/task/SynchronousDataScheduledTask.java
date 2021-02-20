package com.patrickstar.blog.task;/*
 * @PackageName: com.patrickstar.blog.task
 * @ClassName: SynchronousDataScheduledTask
 * @Description:定时任务，将redis数据同步到数据库中
 * @Author: PatrickStaR
 * @Date: 2/8/2020 10:50 AM
 */

import com.patrickstar.blog.service.BlogService;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class SynchronousDataScheduledTask {

    /**
     * redis工具类
    **/
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 博客服务层
     **/
    @Autowired
    private BlogService blogService;

    /**
     * 同步文章访问量，每天凌晨4:30执行
    **/
    @Scheduled(cron = "0 30 4 ? * *")
    public void syncViewCount(){
        System.out.println("======================开始 同步文章访问量======================");
        Long startTime = System.currentTimeMillis();
        Set<String> keySet = redisTemplate.keys("文章*");
        if(keySet != null){
            for(String key : keySet){
                blogService.updateBlogViewCount(Integer.valueOf(key.substring(2)), Integer.valueOf(redisTemplate.opsForValue().get(key)));
            }
        }
        Long endTime =  System.currentTimeMillis();
        System.out.println("本次文章访问量同步成功, 总耗时: " + (endTime - startTime) + "ms");
        System.out.println("======================结束 文章访问量结束======================");
    }
}
