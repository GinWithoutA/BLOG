package com.patrickstar.blog.controller.admin;/*
 * @PackageName: com.patrickstar.blog.controller
 * @ClassName: AdminController
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/30/2019 5:12 PM
 */

import com.patrickstar.blog.entity.Blog;
import com.patrickstar.blog.entity.Category;
import com.patrickstar.blog.service.BlogService;
import com.patrickstar.blog.service.CategoryService;
import com.patrickstar.blog.service.TagService;
import com.patrickstar.blog.service.UserService;
import com.patrickstar.blog.utils.MD5Utils;
import com.patrickstar.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author PatrickStaR
**/
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    /**
     * 页面大小
     **/
    private static final Integer PAGE_SIZE = 10;

    @Autowired
    private PageUtils pageUtils;

    /**
     * 用户服务层
    **/
    @Autowired
    private UserService userService;

    /**
     * 分类服务层
     **/
    @Autowired
    private CategoryService categoryService;

    /**
     * 标签服务层
     **/
    @Autowired
    private TagService tagService;

    /**
     * 博客服务层
     **/
    @Autowired
    private BlogService blogService;

    /**
     * @return java.lang.String
     *
     * 跳转到后台管理登录界面
    **/
    @RequestMapping(value = "")
    public String goAdminLogin(){
        return "admin/login";
    }

    /**
     * @date 5:15 PM 10/30/2019
     * @param username 用户名
     * @param password 密码
     * @return java.lang.String
     * 判断用户名和密码是否正确
    **/
    @RequestMapping(value = "/login")
    public String checkUserPass(String username, String password, RedirectAttributes attributes, HttpSession session){
        String returnPass = userService.getUserPass(username);
        if(!Objects.equals(MD5Utils.code(password), returnPass)){
            attributes.addFlashAttribute("message", "用户名或密码错误！");
            return "redirect:/admin/";
        } else {
            session.setAttribute("loginUser" ,username);
            return "admin/index";
        }
    }

    /**
     * 博客后台管理首页
     *
     * @author PatrickStaR
     * @date 1:22 PM 10/31/2019
    **/
    @RequestMapping(value = "/index")
    public String goAdminIndex(){
        return "admin/index";
    }

    /**
     * 用户注销
     *
     * @author PatrickStaR
     * @date 10:58 AM 10/31/2019
    **/
    @RequestMapping(value = "/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/admin/";
    }

    /**
     * 进入博客列表管理
     *
     * @author PatrickStaR
     * @date 10:58 AM 10/31/2019
    **/
    @RequestMapping(value = "/blog_management_list")
    public String goBlogList(Model model){
        model.addAttribute("indexPage", pageUtils.getRequirePage(blogService.getAllBlogAdmin(), 1, PAGE_SIZE));
        model.addAttribute("allCategory", categoryService.getAllCategoryAdmin());
        model.addAttribute("pageNum", pageUtils.getPageNum(blogService.getAllBlog(), PAGE_SIZE));
        model.addAttribute("currentPage", 1);
        if((Integer)model.getAttribute("pageNum") > 1){
            model.addAttribute("nextPage", 2);
        }
        return "admin/blog_management_list";
    }

    /**
     * 进入博客发布管理
     *
     * @author PatrickStaR
     * @date 11:00 AM 10/31/2019
    **/
    @RequestMapping(value = "/blog_management_post")
    public String goBlogPost(Model model){
        model.addAttribute("allCategory", categoryService.getAllCategoryAdmin());
        model.addAttribute("allTag", tagService.getAllTag());
        model.addAttribute("editBlog", new Blog());
        return "admin/blog_management_post";
    }

    /**
     * 进入分类管理
     *
     * @author PatrickStaR
     * @date 1:14 PM 10/31/2019
    **/
    @RequestMapping(value = "/category_management")
    public String goBlogCategory(Model model){
        model.addAttribute("indexPage", pageUtils.getRequirePage(categoryService.getAllCategoryAdmin(), 1, PAGE_SIZE));
        model.addAttribute("category", new Category());
        model.addAttribute("pageNum", pageUtils.getPageNum(categoryService.getAllCategoryAdmin(), PAGE_SIZE));
        model.addAttribute("currentPage", 1);
        model.addAttribute("allCategory", categoryService.getAllCategoryAdmin());
        if((Integer)model.getAttribute("pageNum") > 1){
            model.addAttribute("nextPage", 2);
        }
        return "admin/category_management";
    }

    /**
     * 进入标签管理
    **/
    @RequestMapping(value = "/tag_management")
    public String goBlogTag(Model model){
        model.addAttribute("indexPage", pageUtils.getRequirePage(tagService.getAllTag(), 1, PAGE_SIZE));
        model.addAttribute("pageNum", pageUtils.getPageNum(tagService.getAllTag(), PAGE_SIZE));
        model.addAttribute("currentPage", 1);
        model.addAttribute("allTag", tagService.getAllTagAdmin());
        if((Integer)model.getAttribute("pageNum") > 1){
            model.addAttribute("nextPage", 2);
        }
        return "admin/tag_management";
    }

    /**
     * 分页请求处理
     *
     * @param requirePage 请求的页面
     * @param type 请求的类型：分类，博客列表，标签，默认为博客列表
     **/
    @RequestMapping(value = "/page")
    public String pageProcessing(Integer requirePage, String type, Model model){
        int pageNum = 0;
        if("category".equals(type)){
            model.addAttribute("indexPage", pageUtils.getRequirePage(categoryService.getAllCategoryAdmin(), requirePage, PAGE_SIZE));
            pageNum = pageUtils.getPageNum(categoryService.getAllCategoryAdmin(), PAGE_SIZE);
            model.addAttribute("category", new Category());
            model.addAttribute("allCategory", categoryService.getAllCategoryAdmin());
        } else if("tag".equals(type)){
            model.addAttribute("indexPage", pageUtils.getRequirePage(tagService.getAllTag(), requirePage, PAGE_SIZE));
            pageNum = pageUtils.getPageNum(tagService.getAllTag(), PAGE_SIZE);
        } else {
            model.addAttribute("indexPage", pageUtils.getRequirePage(blogService.getAllBlog(), requirePage, PAGE_SIZE));
            pageNum = pageUtils.getPageNum(blogService.getAllBlog(), PAGE_SIZE);
        }
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("currentPage", requirePage);
        if(requirePage < pageNum){
            model.addAttribute("nextPage", requirePage + 1);
        }
        if(requirePage != 1){
            model.addAttribute("prevPage", requirePage - 1);
        }
        if("category".equals(type)){
            return "admin/category_management";
        } else if ("tag".equals(type)){
            model.addAttribute("allTag", tagService.getAllTagAdmin());
            return "admin/tag_management";
        } else {
            return "admin/blog_management_list";
        }
    }
}
