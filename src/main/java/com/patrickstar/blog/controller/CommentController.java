package com.patrickstar.blog.controller;/*
 * @PackageName: com.patrickstar.blog.controller
 * @ClassName: CommentController
 * @Description:
 * @Author: PatrickStaR
 * @Date: 11/11/2019 2:09 PM
 */

import com.patrickstar.blog.entity.Comment;
import com.patrickstar.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    /**
     * 评论服务层
    **/
    @Autowired
    private CommentService commentService;


    /**
     * 获取该博客的所有评论
     **/
    @GetMapping(value = "/comments/{blogId}")
    public String comments(@PathVariable Integer blogId, Model model){
        System.out.println(blogId);
        model.addAttribute("comments", commentService.getAllComment(blogId));
        return "detail :: commentList";
    }

    /**
     * 处理新评论
    **/
    @RequestMapping(value = "/leave_message")
    public String reply(Comment comment, String nickname, Model model, HttpSession session){
        if(session.getAttribute("loginUser") != null){
            comment.setIsAdmin(true);
        } else {
            comment.setIsAdmin(false);
        }
        comment.setNickName(nickname);
        System.out.println(comment);
        commentService.addComment(comment);
        return "redirect:/comment/comments/" + comment.getBlog().getId();
    }
}
