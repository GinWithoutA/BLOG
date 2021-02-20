package com.patrickstar.blog.controller.admin;/*
 * @PackageName: com.patrickstar.blog.controller
 * @ClassName: BlogController
 * @Description:
 * @Author: PatrickStaR
 * @Date: 11/4/2019 2:31 PM
 */

import com.patrickstar.blog.entity.Blog;
import com.patrickstar.blog.entity.BlogTag;
import com.patrickstar.blog.service.BlogService;
import com.patrickstar.blog.service.BlogTagService;
import com.patrickstar.blog.service.CategoryService;
import com.patrickstar.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    /**
     * 博客服务层
     **/
    @Autowired
    private BlogService blogService;

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
     * 博客标签关系服务层
     **/
    @Autowired
    private BlogTagService blogTagService;

    /**
     * redis工具类
     **/
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 发布博客，更新博客，根据传来的blog的id是否为空判断此时的状态是新增还是更新
    **/
    @Transactional
    @RequestMapping(value = "/add")
    public String addBlog(Blog blog, RedirectAttributes attributes, String tagIds, Model model){
        if(blog.getId() == null){
            //此时是新增
            blog.setViewCount(0);
            int resultBlog = blogService.addBlog(blog);
            if(resultBlog == 0){
                attributes.addFlashAttribute("message", "发布失败了！再试试呗~~");
                return "redirect:/admin/blog_management_post";
            } else {
                //添加博客分类
                //新增后blog的category_id存在，则getCategory不会报错
                blog.setBelongCategory(categoryService.getCategory(blog.getBelongCategory().getId()));
                //添加博客标签，并更新博客标签表
                if(tagIds != null && !"".equals(tagIds)){
                    List<Integer> tagIdList = new ArrayList<>();
                    for(String tagId : tagIds.split(",")) {
                        if(blogTagService.addBlogTag(new BlogTag(blog.getId(), Integer.valueOf(tagId))) == 0){
                            attributes.addFlashAttribute("message", "发布失败了！再试试呗~~");
                            return "redirect:/admin/blog_management_post";
                        }
                    }
                }
                attributes.addFlashAttribute("message", "发布成功啦！");
                return "redirect:/admin/blog_management_list";
            }
        } else {
            //此时是更新
            //获取更新时间
            blog.setModifyTime(new Timestamp(System.currentTimeMillis()));
            //不用更新浏览次数，因为浏览次数都存放在redis中，每天凌晨会自动同步到数据库中，没有必要自己更新
            //删除原有的博客标签
            blogTagService.deleteBlogTag(blog.getId());
            //更新博客标签
            if(tagIds != null && !"".equals(tagIds)){
                List<Integer> tagIdList = new ArrayList<>();
                for(String tagId : tagIds.split(",")){
                    blogTagService.addBlogTag(new BlogTag(blog.getId(), Integer.valueOf(tagId)));
                }
            }
            //更新博客
            blogService.updateBlog(blog);
            attributes.addFlashAttribute("message", "发布成功啦！");
            return "redirect:/admin/blog_management_list";
        }

    }

    /**
     * 删除博客
    **/
    @Transactional
    @RequestMapping(value = "/delete")
    public String deleteBlog(Integer id, RedirectAttributes attributes){
        blogTagService.deleteBlogTag(id);
        int result = blogService.deleteBlog(id);
        if(result != 0){
            attributes.addFlashAttribute("message", "删除成功啦！");
        } else {
            attributes.addFlashAttribute("message", "删除失败了！再试试吧~~~");
        }
        return "redirect:/admin/blog_management_list";
    }

    /**
     * 查找博客
     **/
//    @RequestMapping(value = "/search_blog")
//    public List<Blog> searchBlogList(String name, String category, String tag, boolean recommend, Model model){
//
//    }


    /**
     * 前往博客编辑页面
     **/
    @RequestMapping(value = "/blog_management_post")
    public String goEdit(Integer id, Model model){
        Blog blog = blogService.getBlog(id);
        List<Integer> tagIds = blogTagService.getBlogAllTag(blog.getId());
        //若标签不为空，则取，否则不取
        if(tagIds.size() != 0){
            blog.setTags(tagService.getRequireTag(tagIds));
            //初始化tagIds
            blog.init();
        }
        model.addAttribute("editBlog", blog);
        model.addAttribute("allTag", tagService.getAllTag());
        model.addAttribute("allCategory", categoryService.getAllCategoryAdmin());
        return "admin/blog_management_post";
    }

}
