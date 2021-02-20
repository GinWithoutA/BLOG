package com.patrickstar.blog.service;

import com.patrickstar.blog.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * 新增标签
     **/
    Integer addTag(Tag tag);
    /**
     * 删除标签
     **/
    Integer deleteTag(Integer id);
    /**
     * 更新标签
     **/
    Integer updateTag(Tag tag);
    /**
     * 获取标签
     **/
    Tag getTag(Integer id);
    /**
     * 获取所有标签
     **/
    List<Tag> getAllTag();
    /**
     * 获取所有标签(管理)
     **/
    List<Tag> getAllTagAdmin();
    /**
     * 获取所有标签(非管理)
     **/
    List<Tag> getAllTagIndex();
    /**
     * 动态查询获取所有标签
     **/
    List<Tag> getRequireTag(List<Integer> tagIds);
}
