package com.patrickstar.blog.entity;/*
 * @PackageName: com.patrickstar.blog.entity
 * @ClassName: Category
 * @Description:分类实体类
 * @Author: PatrickStaR
 * @Date: 10/30/2019 3:03 PM
 */

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PatrickStaR
**/
public class Category {

    /**
     * 分类序列号
    **/
    private Integer id;
    /**
     * 分类名称
    **/
    @NotBlank(message = "分类名不能为空")
    private String name;
    /**
     * 分类对应博客列表
    **/
    private List<Blog> blogs;


    public Category(){
        blogs = new ArrayList<>();
    }

    public Category(Integer id, String name){
        this.id = id;
        this.name = name;
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
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
