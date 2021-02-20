package com.patrickstar.blog.dao;

import com.patrickstar.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {
    /**
     * 新增分类
     **/
    Integer addCategory(Category category);
    /**
     * 删除分类
     **/
    Integer deleteCategory(Integer id);
    /**
     * 更新分类
     **/
    Integer updateCategory(Category category);
    /**
     * 获取分类
     **/
    Category getCategory(Integer id);
    /**
     * 获取所有分类
     **/
    List<Category> getAllCategory();
    /**
     * 获取所有分类(管理)
     **/
    List<Category> getAllCategoryAdmin();
    /**
     * 获取所有分类(非管理)
     **/
    List<Category> getAllCategoryIndex();
}
