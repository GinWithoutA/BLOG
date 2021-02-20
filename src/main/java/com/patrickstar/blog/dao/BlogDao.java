package com.patrickstar.blog.dao;/*
 * @PackageName: com.patrickstar.blog.dao
 * @ClassName: BlogDao
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/30/2019 3:34 PM
 */

import com.patrickstar.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogDao {
    /**
     * 新增博客
     **/
    Integer addBlog(Blog blog);
    /**
     * 删除博客
     **/
    Integer deleteBlog(Integer id);
    /**
     * 更新博客
     **/
    Integer updateBlog(Blog blog);
    /**
     * 更新博客
     **/
    Integer updateBlogViewCount(Integer id, Integer viewCount);
    /**
     * 获取博客
     **/
    Blog getBlog(Integer id);
    /**
     * 获取所有博客
     **/
    List<Blog> getAllBlog();
    /**
     * 获取所有博客(管理)
     **/
    List<Blog> getAllBlogAdmin();
    /**
     * 获取最新博客（取前6个最新博客）
     **/
    List<Blog> getLatestBlog();
    /**
     * 获取分类拥有的博客
     **/
    List<Blog> getCategoryBlog(Integer categoryId);
    /**
     * 获取标签拥有的博客
     **/
    List<Blog> getTagBlog(Integer tagId);
    /**
     * 查询博客
     **/
    List<Blog> searchBlog(String target);
    /**
     * 获取博客年份月份
     **/
    List<String> getBlogYear();
    /**
     * 根据年份和月份获取博客列表
     **/
    List<Blog> getBlogByYearMonth(String yearAndMonth);
}
