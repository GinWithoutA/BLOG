package com.patrickstar.blog.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author PatrickStaR
**/
@Mapper
public interface UserDao {
    /**
     * 根据用户名获取密码
    **/
    String getUserPass(String userName);
}
