package com.patrickstar.blog.service.impl;/*
 * @PackageName: com.patrickstar.blog.service.impl
 * @ClassName: CategoryServiceImpl
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/31/2019 1:54 PM
 */

import com.patrickstar.blog.dao.CategoryDao;
import com.patrickstar.blog.entity.Category;
import com.patrickstar.blog.exception.NotFindException;
import com.patrickstar.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Integer addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    @Override
    public Integer deleteCategory(Integer id) {
        return categoryDao.deleteCategory(id);
    }

    @Override
    public Integer updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    @Override
    public Category getCategory(Integer id) throws NotFindException {
        Category category = categoryDao.getCategory(id);
        if(category == null){
            throw new NotFindException("不存在此分类，要不再试试？");
        } else {
            return category;
        }
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    @Override
    public List<Category> getAllCategoryAdmin() {
        return categoryDao.getAllCategoryAdmin();
    }

    @Override
    public List<Category> getAllCategoryIndex() {
        return categoryDao.getAllCategoryIndex();
    }

}
