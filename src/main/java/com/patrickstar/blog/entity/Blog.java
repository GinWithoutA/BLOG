package com.patrickstar.blog.entity;/*
 * @PackageName: com.patrickstar.blog.entity
 * @ClassName: Blog
 * @Description:博客实体类
 * @Author: PatrickStaR
 * @Date: 10/30/2019 3:09 PM
 */

import java.sql.Timestamp;
import java.util.List;

/**
 * @author PatrickStaR
**/
public class Blog {
    /**
     * 博客序列号
    **/
    private Integer id;
    /**
     * 博客标题
    **/
    private String title;
    /**
     * 博客描述
     **/
    private String describe;
    /**
     * 博客内容
    **/
    private String content;
    /**
     * 博客封面
    **/
    private String cover;
    /**
     * 博客类型：原创，转载，翻译
    **/
    private String type;
    /**
     * 博客浏览次数
    **/
    private Integer viewCount;
    /**
     * 是否推荐给访客
    **/
    private boolean recommend;
    /**
     * 该文章是否需要赞赏
    **/
    private boolean appreciation;
    /**
     * 是否可以评论
    **/
    private boolean comment;
    /**
     * 是否要转载声明
    **/
    private boolean sharable;
    /**
     * 是否在草稿箱
    **/
    private boolean drafts;
    /**
     * 发布时间
    **/
    private Timestamp publishTime;
    /**
     * 更新时间
    **/
    private Timestamp modifyTime;
    /**
     * 博客标签，一个博客可以对应多个标签
    **/
    private List<Tag> tags;
    /**
     * 博客标签id，一个博客可以对应多个标签
     **/
    private String tagIds;
    /**
     * 博客分类，一个博客对应一个分类
    **/
    private Category belongCategory;
    /**
     * 博客评论，一个博客可以有多条评论
     **/
    private List<Comment> comments;


    public Blog(){
        this.belongCategory = new Category();
    }

    public Blog(Integer id, String title, String describe, String content, String cover, String type, Integer viewCount, boolean recommend, boolean appreciation, boolean comment, boolean sharable, boolean drafts, Timestamp publishTime, Timestamp modifyTime, List<Tag> tags, String tagIds, Category belongCategory, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.describe = describe;
        this.content = content;
        this.cover = cover;
        this.type = type;
        this.viewCount = viewCount;
        this.recommend = recommend;
        this.appreciation = appreciation;
        this.comment = comment;
        this.sharable = sharable;
        this.drafts = drafts;
        this.publishTime = publishTime;
        this.modifyTime = modifyTime;
        this.tags = tags;
        this.tagIds = tagIds;
        this.belongCategory = belongCategory;
        this.comments = comments;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isSharable() {
        return sharable;
    }

    public void setSharable(boolean sharable) {
        this.sharable = sharable;
    }

    public boolean isDrafts() {
        return drafts;
    }

    public void setDrafts(boolean drafts) {
        this.drafts = drafts;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Category getBelongCategory() {
        return belongCategory;
    }

    public void setBelongCategory(Category belongCategory) {
        this.belongCategory = belongCategory;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public void init(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Tag tag : this.getTags()){
            stringBuilder.append(tag.getId());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        this.setTagIds(stringBuilder.toString());
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", describe='" + describe + '\'' +
                ", content='" + content + '\'' +
                ", cover='" + cover + '\'' +
                ", type='" + type + '\'' +
                ", viewCount=" + viewCount +
                ", recommend=" + recommend +
                ", appreciation=" + appreciation +
                ", comment=" + comment +
                ", sharable=" + sharable +
                ", drafts=" + drafts +
                ", publishTime=" + publishTime +
                ", modifyTime=" + modifyTime +
                ", tags=" + tags +
                ", tagIds='" + tagIds + '\'' +
                ", belongCategory=" + belongCategory +
                ", comments=" + comments +
                '}';
    }
}
