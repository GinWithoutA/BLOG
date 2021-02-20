package com.patrickstar.blog.controller.admin;/*
 * @PackageName: com.patrickstar.blog.controller
 * @ClassName: TagController
 * @Description:
 * @Author: PatrickStaR
 * @Date: 11/1/2019 11:12 AM
 */

import com.patrickstar.blog.entity.Tag;
import com.patrickstar.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 删除标签
    **/
    @RequestMapping(value = "/delete")
    public String deleteTag(Integer id, RedirectAttributes attributes){
        int result = tagService.deleteTag(id);
        if(result != 0){
            attributes.addFlashAttribute("message", "删除成功啦！");
        } else {
            attributes.addFlashAttribute("message", "删除失败了！再试试吧~~~");
        }
        return "redirect:/admin/tag_management";
    }

    /**
     * 更新标签
     **/
    @RequestMapping(value = "/update")
    public String updateTag(Tag tag, RedirectAttributes attributes){
        int result = tagService.updateTag(tag);
        if(result != 0){
            attributes.addFlashAttribute("message", "更新成功啦！看看下面把👇");
        } else {
            attributes.addFlashAttribute("message", "更新失败啦！要不重新试试~~");
        }
        return "redirect:/admin/tag_management";
    }

    /**
     * 添加标签
     **/
    @RequestMapping(value = "add")
    public String addTag(Tag tag, RedirectAttributes attributes){
        int result = tagService.addTag(tag);
        if(result == 0){
            attributes.addFlashAttribute("message", "添加失败了！再试试呗~~");
        } else {
            attributes.addFlashAttribute("message", "添加成功啦！");
        }
        return "redirect:/admin/tag_management";
    }

}
