package com.patrickstar.blog.utils;/*
 * @PackageName: com.patrickstar.blog.utils
 * @ClassName: PageUtils
 * @Description:分页工具类
 * @Author: PatrickStaR
 * @Date: 11/6/2019 1:53 PM
 */

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component(value = "pageUtils")
public class PageUtils<T> {

    /**
     * 分页实现：获取请求页面的数据
     *
     * @param list 要分页的数据
     * @param indexPage 要获取哪一页的数据
     * @param pageSize 每一页有多少条数据
    **/
    public List<T> getRequirePage(List<T> list, Integer indexPage, Integer pageSize){
        List<T> resultList = new ArrayList<>();
        int i = (indexPage - 1) * pageSize;
        while(i < indexPage * pageSize){
            if(i < list.size()){
                resultList.add(list.get(i));
            }
            i++;
        }
        return resultList;
    }

    /**
     * 分页实现:获取分页的数量
     *
     * @param list 要分页的数据
     * @param pageSize 每一页有多少条数据
     **/
    public Integer getPageNum(List<T> list, Integer pageSize){
        if(list.size() == 0){
            return 1;
        }
        int num = list.size() / pageSize;
        //若除完后还有余数，则余数就是新的一页的数据，返回当前页数加1
        if(list.size() % pageSize != 0){
            return num + 1;
        }
        //整除，没有多余的数据，直接返回
        return num;
    }
}
