package com.patrickstar.blog.dao;

import com.patrickstar.blog.entity.Blog;
import com.patrickstar.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    /**
     * 新增评论
     **/
    Integer addComment(Comment comment);
    /**
     * 删除评论
     **/
    Integer deleteComment(Integer id);
    /**
     * 更新评论
     **/
    Integer updateComment(Comment comment);
    /**
     * 获取评论
     **/
    Blog getComment(Integer id);
    /**
     * 获取该篇博客所有关联评论
     **/
    List<Comment> getAllComment(Integer blogId);
    /**
     * 获取该条评论的子评论
     **/
    List<Comment> getCommentChildren(Integer blogId, Integer commentId);
}
