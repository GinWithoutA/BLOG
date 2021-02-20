package com.patrickstar.blog.entity;
/*
 * @PackageName: com.patrickstar.blog.entity
 * @ClassName: Comment
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/30/2019 3:39 PM
 */

import java.sql.Timestamp;
import java.util.List;

/**
 * @author PatrickStaR
**/
public class Comment {
    /**
     * 评论序列号
     **/
    private Integer id;
    /**
     * 昵称
     **/
    private String nickName;
    /**
     * 头像
     **/
    private String headPortrait;
    /**
     * 评论内容
     **/
    private String content;
    /**
     * 点赞数
     **/
    private Integer liked;
    /**
     * 踩数
     **/
    private Integer disliked;
    /**
     * 评论时间
     **/
    private Timestamp commentTime;
    /**
     * 一条评论对应一个博客
    **/
    private Blog blog;
    /**
     * 一条评论可以有多条回复
    **/
    private List<Comment> comments;
    /**
     * 该条评论对应的父评论
     **/
    private Comment parent;
    /**
     * 用户邮箱
     **/
    private String email;
    /**
     * 管理员评论标志
     **/
    private boolean isAdmin;

    public Comment(){

    }

    public Comment(Integer id, String nickName, String headPortrait, String content, Integer liked, Integer disliked, Timestamp commentTime, Blog blog, List<Comment> comments, Comment parent, String email, boolean isAdmin) {
        this.id = id;
        this.nickName = nickName;
        this.headPortrait = headPortrait;
        this.content = content;
        this.liked = liked;
        this.disliked = disliked;
        this.commentTime = commentTime;
        this.blog = blog;
        this.comments = comments;
        this.parent = parent;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public Integer getDisliked() {
        return disliked;
    }

    public void setDisliked(Integer disliked) {
        this.disliked = disliked;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", content='" + content + '\'' +
                ", liked=" + liked +
                ", disliked=" + disliked +
                ", commentTime=" + commentTime +
                ", blog=" + blog +
                ", comments=" + comments +
                ", parent=" + parent +
                ", email=" +email +
                ", isAdmin=" +isAdmin +
                '}';
    }
}
