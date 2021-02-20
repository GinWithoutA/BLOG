package com.patrickstar.blog.entity;/*
 * @PackageName: com.patrickstar.blog.entity
 * @ClassName: Tag
 * @Description:标签实体类
 * @Author: PatrickStaR
 * @Date: 10/30/2019 3:06 PM
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author PatrickStaR
 */
public class Tag {

    /**
     * 标签序列号
    **/
    private Integer id;
    /**
     * 标签名称
    **/
    private String name;
    /**
     * 标签对应博客列表
    **/
    private List<Blog> blogs;

    public Tag(){
        this.blogs = new ArrayList<>();
    }

    public Tag(Integer id, String name, List<Blog> blogs){
        this.id = id;
        this.name = name;
        this.blogs = blogs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
