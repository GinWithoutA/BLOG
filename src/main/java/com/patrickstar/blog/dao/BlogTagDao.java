package com.patrickstar.blog.dao;

import com.patrickstar.blog.entity.BlogTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogTagDao {

    /**
     * 新增博客标签关系
     **/
    Integer addBlogTag(BlogTag blogTag);

    /**
     * 删除博客标签关系(根据博客ID)
     **/
    Integer deleteBlogTag(Integer blogId);

    /**
     * 获取博客所有标签
     **/
    List<Integer> getBlogAllTag(Integer blogId);


}
