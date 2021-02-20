package com.patrickstar.blog.controller;/*
 * @PackageName: com.patrickstar.blog.controller
 * @ClassName: IndexController
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/30/2019 11:05 AM
 */

import com.patrickstar.blog.entity.Blog;
import com.patrickstar.blog.entity.Category;
import com.patrickstar.blog.service.BlogService;
import com.patrickstar.blog.service.CategoryService;
import com.patrickstar.blog.service.CommentService;
import com.patrickstar.blog.service.TagService;
import com.patrickstar.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    /**
     * 分页数据每页的大小
    **/
    private static final Integer PAGE_SIZE = 5;

    /**
     * 分页工具类
    **/
    @Autowired
    private PageUtils pageUtils;

    /**
     * 博客工具类
     **/
    @Autowired
    private BlogService blogService;

    /**
     * 标签服务层
     **/
    @Autowired
    private TagService tagService;

    /**
     * 分类服务层
     **/
    @Autowired
    private CategoryService categoryService;

    /**
     * 评论服务层
     **/
    @Autowired
    private CommentService commentService;

    /**
     * redis工具类
    **/
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 从redis中获取viewcount更新到blogs中
     *
     * @param blogs 需要更新的博客列表
    **/
    public List<Blog> CorrectViewCount(List<Blog> blogs){
        for(Blog blog : blogs){
            if(null != redisTemplate.opsForValue().get("文章" + blog.getId())){
                blog.setViewCount(Integer.valueOf(redisTemplate.opsForValue().get("文章" + blog.getId())));
            }
        }
        return blogs;
    }

    /**
     * 首页
     **/
    @RequestMapping("/")
    public String index(Model model) {
        List<Blog> blogs = blogService.getAllBlog();
        int pageNum = pageUtils.getPageNum(blogs, PAGE_SIZE);
        //将总页数存放到request域中
        model.addAttribute("pageNum", pageNum);
        //将当前页面（第一页）的博客信息存放到request域中
        model.addAttribute("indexPage", CorrectViewCount(pageUtils.getRequirePage(blogs, 1, PAGE_SIZE)));
        //将所有标签存放到request域中
        model.addAttribute("allTag", tagService.getAllTagIndex());
        //将所有分类存放到request域中
        model.addAttribute("allCategory", categoryService.getAllCategory());
        //将所有博客存放到request域中
        model.addAttribute("allBlog", blogService.getAllBlog());
        //若总页数大于1，将下一页（第二页）的页数存放到request域中
        if(pageNum > 1){
            model.addAttribute("nextPage", 2);
        }
        //将当前页面（第一页）的页数存放到request域中
        model.addAttribute("currentPage", 1);
        //获取前六篇最新博客存到request域中
        model.addAttribute("latestBlog", blogService.getLatestBlog());
        return "index";
    }

    /**
     * 切换页面
     *
     * @param requirePage 请求的页面
     * @param type 类型：分类，标签，默认类型（空）
     **/
    @RequestMapping("/page")
    public String goOtherPage(Integer requirePage, Model model, String type, Integer id){
        //默认情况是首页的情况
        String returnPage = "index";
        List<Blog> blogs = blogService.getAllBlog();
        //分类页面传来的分页请求
        if("category".equals(type) && id != null){
            blogs = blogService.getCategoryBlog(id);
        }
        //标签页面传来的分页请求
        if("tag".equals(type) && id != null){
            blogs = blogService.getTagBlog(id);
        }
        int pageNum = pageUtils.getPageNum(blogs, PAGE_SIZE);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("indexPage", CorrectViewCount(pageUtils.getRequirePage(blogs, requirePage, PAGE_SIZE)));
        model.addAttribute("allTag", tagService.getAllTag());
        model.addAttribute("allCategory", categoryService.getAllCategoryIndex());
        model.addAttribute("allBlog", blogService.getAllBlog());
        //获取前六篇最新博客存到request域中
        model.addAttribute("latestBlog", blogService.getLatestBlog());
        //要获取的页面不是最后一页
        if(pageNum != requirePage){
            model.addAttribute("nextPage", requirePage + 1);
        }
        //要获取的页面不是第一页
        if(requirePage != 1){
            model.addAttribute("prevPage", requirePage - 1);
        }
        //将要获取的页面的页数返回
        model.addAttribute("currentPage", requirePage);
        if("category".equals(type)){
            model.addAttribute("currentCategory", id);
            return "category";
        }
        if("tag".equals(type)){
            model.addAttribute("currentTag", id);
            return "tag";
        }
        return "index";
    }

    /**
     * 前往分类详情页面
     **/
    @RequestMapping(value = "/blog_category")
    public String goCategory(Model model){
        List<Blog> blogs = blogService.getAllBlog();
        int pageNum = pageUtils.getPageNum(blogs, PAGE_SIZE);
        //将所有分类存放到request中
        model.addAttribute("allCategory", categoryService.getAllCategoryIndex());
        //将总页数存放到request域中
        model.addAttribute("pageNum", pageNum);
        //将当前页面（第一页）的博客信息存放到request域中
        model.addAttribute("indexPage", CorrectViewCount(pageUtils.getRequirePage(blogService.getAllBlog(), 1, PAGE_SIZE)));
        //若总页数大于1，将下一页（第二页）的页数存放到request域中
        if(pageNum != 1){
            model.addAttribute("nextPage", 2);
        }
        //将当前页面（第一页）的页数存放到request域中
        model.addAttribute("currentPage", 1);
        //获取前六篇最新博客存到request域中
        model.addAttribute("latestBlog", blogService.getLatestBlog());
        return "category";
    }

    /**
     * 前往标签详情页面
     **/
    @RequestMapping(value = "/blog_tag")
    public String goTag(Model model){
        List<Blog> blogs = blogService.getAllBlog();
        for(Blog blog : blogs){
            System.out.println(blog.getTags());
        }
        int pageNum = pageUtils.getPageNum(blogs, PAGE_SIZE);
        //将总页数存放到request域中
        model.addAttribute("pageNum", pageNum);
        //将当前页面（第一页）的博客信息存放到request域中
        model.addAttribute("indexPage", CorrectViewCount(pageUtils.getRequirePage(blogs, 1, PAGE_SIZE)));
        //将所有标签存放到request域中
        model.addAttribute("allTag", tagService.getAllTag());
        //若总页数大于1，将下一页（第二页）的页数存放到request域中
        if(pageNum != 1){
            model.addAttribute("nextPage", 2);
        }
        //将当前页面（第一页）的页数存放到request域中
        model.addAttribute("currentPage", 1);
        //获取前六篇最新博客存到request域中
        model.addAttribute("latestBlog", blogService.getLatestBlog());
        return "tag";
    }

    /**
     * 前往关于我详情页面
     **/
    @RequestMapping(value = "/about_me")
    public String goAbout(Model model){
        model.addAttribute("latestBlog", blogService.getLatestBlog());
        return "about";
    }

    /**
     * 前往归档详情页面
     **/
    @RequestMapping(value = "/blog_archive")
    public String goArchive(Model model){
        model.addAttribute("latestBlog", blogService.getLatestBlog());
        model.addAttribute("allBlog", blogService.getAllBlog());
        List<String> target = blogService.getBlogYear();
        Map<String, List<Blog>> map = new HashMap<>(100);
        for(String yearAndMonth : target){
            map.put(yearAndMonth, blogService.getBlogByYearMonth(yearAndMonth));
        }

        model.addAttribute("blogByYearAndMonth", map);
        return "archive";
    }

    /**
     * 前往详情页面
     *
     * @param id 博客id
     **/
    @RequestMapping(value = "/blog_detail")
    public String goDetail(Integer id, Model model){
        Blog blog = blogService.getAndConvert(id);
        model.addAttribute("comments", commentService.getAllComment(id));
        model.addAttribute("latestBlog", blogService.getLatestBlog());
        //判断redis中是否存在当前博客的浏览量，没有去数据库获取
        if(null == redisTemplate.opsForValue().get("文章" + id)){
            redisTemplate.opsForValue().set("文章" + id, String.valueOf(blog.getViewCount() + 1));
        } else {
            redisTemplate.opsForValue().set("文章" + id, String.valueOf(Integer.valueOf(redisTemplate.opsForValue().get("文章" + id)) + 1));
        }
        blog.setViewCount(Integer.valueOf(redisTemplate.opsForValue().get("文章" + id)));
        model.addAttribute("blog", blog);
        return "detail";
    }

    /**
     * 标签选择
     *
     * @param id 标签ID
     **/
    @RequestMapping(value = "/tag_blog")
    public String getRequireTagBlog (Integer id, Model model){
        List<Blog> blogs = blogService.getTagBlog(id);
        int pageNum = pageUtils.getPageNum(blogs, PAGE_SIZE);
        if(pageNum > 1){
            model.addAttribute("nextPage", 2);
        }
        model.addAttribute("currentPage", 1);
        model.addAttribute("allTag", tagService.getAllTag());
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("currentTag", id);
        model.addAttribute("indexPage", CorrectViewCount(pageUtils.getRequirePage(blogs, 1, PAGE_SIZE)));
        return "tag";
    }

    /**
     * 分类选择
     *
     * @param id 分类id
     **/
    @RequestMapping("/category_blog")
    public String getRequireCategoryBlog(Model model, Integer id){
        List<Blog> blogs = blogService.getCategoryBlog(id);
        //System.out.println(blogs.get(0).getTags());
        int pageNum = pageUtils.getPageNum(blogs, PAGE_SIZE);
        if(pageNum > 1){
            model.addAttribute("nextPage", 2);
        }
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("indexPage", CorrectViewCount(pageUtils.getRequirePage(blogs, 1, PAGE_SIZE)));
        model.addAttribute("currentCategory", id);
        model.addAttribute("allCategory", categoryService.getAllCategoryIndex());
        return "category";
    }

    /**
     * 全局搜索
     *
     * @param target 搜索内容
     **/
    @RequestMapping("/search")
    public String search(Model model, String target){
        List<Blog> blogs = blogService.searchBlog(target);
        int pageNum = pageUtils.getPageNum(blogs, PAGE_SIZE);
        if(pageNum > 1){
            model.addAttribute("nextPage", 2);
        }
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("indexPage", CorrectViewCount(pageUtils.getRequirePage(blogs, 1, PAGE_SIZE)));
        model.addAttribute("latestBlog", blogService.getLatestBlog());
        model.addAttribute("resultSize", blogs.size());
        model.addAttribute("target", target);
        return "search";
    }

}
