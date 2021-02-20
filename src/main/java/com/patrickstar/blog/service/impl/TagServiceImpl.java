package com.patrickstar.blog.service.impl;/*
 * @PackageName: com.patrickstar.blog.service.impl
 * @ClassName: TagServiceImpl
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/31/2019 3:37 PM
 */

import com.patrickstar.blog.dao.TagDao;
import com.patrickstar.blog.entity.Tag;
import com.patrickstar.blog.exception.NotFindException;
import com.patrickstar.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public Integer addTag(Tag tag) {
        return tagDao.addTag(tag);
    }

    @Override
    public Integer deleteTag(Integer id) {
        return tagDao.deleteTag(id);
    }

    @Override
    public Integer updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public Tag getTag(Integer id) throws NotFindException {
        Tag tag = tagDao.getTag(id);
        if(tag == null){
            throw new NotFindException("找不到此标签！要不再试试？");
        } else{
            return tag;
        }
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public List<Tag> getAllTagAdmin() {
        return tagDao.getAllTagAdmin();
    }

    @Override
    public List<Tag> getAllTagIndex() {
        return tagDao.getAllTagIndex();
    }

    @Override
    public List<Tag> getRequireTag(List<Integer> tagIds) {
        return tagDao.getRequireTag(tagIds);
    }
}
