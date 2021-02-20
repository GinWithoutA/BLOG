package com.patrickstar.blog.service.impl;/*
 * @PackageName: com.patrickstar.blog.service.impl
 * @ClassName: BlogServiceImpl
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/31/2019 4:26 PM
 */

import com.patrickstar.blog.dao.BlogDao;
import com.patrickstar.blog.entity.Blog;
import com.patrickstar.blog.exception.NotFindException;
import com.patrickstar.blog.service.BlogService;
import com.patrickstar.blog.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Integer addBlog(Blog blog) {
        return blogDao.addBlog(blog);
    }

    @Override
    public Integer deleteBlog(Integer id) {
        return blogDao.deleteBlog(id);
    }

    @Override
    public Integer updateBlog(Blog blog) {
        return blogDao.updateBlog(blog);
    }

    @Override
    public Integer updateBlogViewCount(Integer id, Integer viewCount) {
        return blogDao.updateBlogViewCount(id, viewCount);
    }

    @Override
    public Blog getBlog(Integer id) throws NotFindException{
        Blog blog = blogDao.getBlog(id);
        if(blog == null){
            throw new NotFindException("不存在此博客，要不再试试？");
        } else {
            return blog;
        }
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Override
    public List<Blog> getAllBlogAdmin() {
        return blogDao.getAllBlogAdmin();
    }

    @Override
    public List<Blog> getLatestBlog() {
        return blogDao.getLatestBlog();
    }

    @Override
    public List<Blog> getCategoryBlog(Integer categoryId) {
        return blogDao.getCategoryBlog(categoryId);
    }

    @Override
    public List<Blog> getTagBlog(Integer tagId) {
        return blogDao.getTagBlog(tagId);
    }

    @Override
    public List<Blog> searchBlog(String target) {
        return blogDao.searchBlog(target);
    }

    @Override
    public Blog getAndConvert(Integer id) throws NotFindException {
        Blog blog = blogDao.getBlog(id);
        if(blog == null){
            throw new NotFindException("找不到啦~~~");
        }
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    @Override
    public List<String> getBlogYear() {
        return blogDao.getBlogYear();
    }

    @Override
    public List<Blog> getBlogByYearMonth(String yearAndMonth) {
        return blogDao.getBlogByYearMonth(yearAndMonth);
    }


}
