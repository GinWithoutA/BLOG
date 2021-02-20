package com.patrickstar.blog.controller.admin;/*
 * @PackageName: com.patrickstar.blog.controller
 * @ClassName: CategoryController
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/31/2019 2:44 PM
 */

import com.patrickstar.blog.entity.Category;
import com.patrickstar.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 删除分类
    **/
    @RequestMapping(value = "/delete")
    public String deleteCategory(Integer id, RedirectAttributes attributes){
        int result = categoryService.deleteCategory(id);
        if(result != 0){
            attributes.addFlashAttribute("message", "删除成功啦！");
        } else {
            attributes.addFlashAttribute("message", "删除失败了！再试试吧~~~");
        }
        return "redirect:/admin/category_management";
    }

    /**
     * 增加分类
     **/
    @RequestMapping(value = "/add")
    public String addCategoty(@Valid Category category, BindingResult bindingResult, RedirectAttributes attributes) {
        if(bindingResult.hasErrors()){
            return "admin/category_management";
        }
        int result = categoryService.addCategory(category);
        if(result == 0){
            attributes.addFlashAttribute("message", "添加失败了！再试试呗~~");
        } else {
            attributes.addFlashAttribute("message", "添加成功啦！");
        }
        return "redirect:/admin/category_management";
    }
    /**
     * 更新分类
     **/
    @RequestMapping(value = "/update")
    public String updateCategory(Category category, RedirectAttributes attributes){
        int result = categoryService.updateCategory(category);
        if(result != 0){
            attributes.addFlashAttribute("message", "更新成功啦！看看下面把👇");
        } else {
            attributes.addFlashAttribute("message", "更新失败啦！要不重新试试~~");
        }
        return "redirect:/admin/category_management";
    }
}
