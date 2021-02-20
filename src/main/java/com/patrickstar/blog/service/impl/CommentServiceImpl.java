package com.patrickstar.blog.service.impl;/*
 * @PackageName: com.patrickstar.blog.service.impl
 * @ClassName: CommentServiceImpl
 * @Description:
 * @Author: PatrickStaR
 * @Date: 11/4/2019 3:19 PM
 */

import com.patrickstar.blog.dao.CommentDao;
import com.patrickstar.blog.entity.Blog;
import com.patrickstar.blog.entity.Comment;
import com.patrickstar.blog.service.CommentService;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    /**
     * 临时存放回复评论的集合
    **/
    private List<Comment> tmpReply;

    /**
     * 评论接口层
    **/
    @Autowired
    private CommentDao commentDao;

    @Override
    public Integer addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public Integer deleteComment(Integer id) {
        return null;
    }

    @Override
    public Integer updateComment(Comment comment) {
        return null;
    }

    @Override
    public Blog getComment(Integer id) {
        return null;
    }

    @Override
    public List<Comment> getAllComment(Integer blogId) {
        //首先取出最顶层的评论
        List<Comment> aboveComment = commentDao.getAllComment(blogId);
        //循环每个顶级元素，合并孩子
        List<Comment> result = new ArrayList<>();
        for(Comment comment : aboveComment){
            //实例化一个匿名类存放当前父评论的所有子评论
            comment.setComments(new ArrayList<>());
            result.add(combineChildren(comment, comment, blogId));
        }
        for(Comment comment : result){
            System.out.println(comment.getComments());
        }
        return result;
    }

    @Override
    public List<Comment> getCommentChildren(Integer blogId, Integer commentId) {
        return commentDao.getCommentChildren(blogId, commentId);
    }

    /**
     * @param comment 第一级评论
     * @param currentComment 当前的评论对象
     * @param blogId 评论所属的博客
     * @return com.patrickstar.blog.entity.Comment
    **/
    public Comment combineChildren(Comment comment, Comment currentComment, Integer blogId){
        //获取当前父评论的第一层子评论
        List<Comment> reply = commentDao.getCommentChildren(blogId, currentComment.getId());
        //若没有子评论了，返回上一层
        if(reply.size() == 0){
            return currentComment;
        }
        //循环每个子评论
        for(Comment children : reply){
            //把所有的子评论全部放到comment父评论的comments集合中
            comment.getComments().add(combineChildren(comment, children, blogId));
        }
        return currentComment;
    }
}
