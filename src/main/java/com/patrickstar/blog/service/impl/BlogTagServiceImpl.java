package com.patrickstar.blog.service.impl;/*
 * @PackageName: com.patrickstar.blog.service.impl
 * @ClassName: BlogTagServiceImpl
 * @Description:
 * @Author: PatrickStaR
 * @Date: 11/5/2019 4:27 PM
 */

import com.patrickstar.blog.dao.BlogTagDao;
import com.patrickstar.blog.entity.BlogTag;
import com.patrickstar.blog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "blogTagService")
public class BlogTagServiceImpl implements BlogTagService {

    /**
     * 博客标签关系dao层
     **/
    @Autowired
    private BlogTagDao blogTagDao;

    @Override
    public Integer addBlogTag(BlogTag blogTag) {
        return blogTagDao.addBlogTag(blogTag);
    }

    /**
     * 删除博客标签关系(根据博客ID)
     **/
    @Override
    public Integer deleteBlogTag(Integer blogId) {
        return blogTagDao.deleteBlogTag(blogId);
    }

    /**
     * 获取博客所有标签
     **/
    @Override
    public List<Integer> getBlogAllTag(Integer blogId){
        return blogTagDao.getBlogAllTag(blogId);
    }
}
