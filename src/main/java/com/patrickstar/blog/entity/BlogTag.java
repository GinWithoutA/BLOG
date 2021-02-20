package com.patrickstar.blog.entity;/*
 * @PackageName: com.patrickstar.blog.entity
 * @ClassName: BlogTag
 * @Description:博客标签实体类
 * @Author: PatrickStaR
 * @Date: 11/5/2019 4:23 PM
 */

public class BlogTag {

    /**
     * 主键ID
    **/
    private Integer id;

    /**
     * 博客ID
     **/
    private Integer blogId;

    /**
     * 标签ID
     **/
    private Integer tagId;

    public BlogTag(){

    }

    public BlogTag(Integer blogId, Integer tagId){
        this.blogId = blogId;
        this.tagId = tagId;
    }

    public BlogTag(Integer id, Integer blogId, Integer tagId) {
        this.id = id;
        this.blogId = blogId;
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "BlogTag{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", tagId=" + tagId +
                '}';
    }
}
