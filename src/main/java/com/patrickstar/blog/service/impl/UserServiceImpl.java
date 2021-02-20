package com.patrickstar.blog.service.impl;/*
 * @PackageName: com.patrickstar.blog.service.impl
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/30/2019 3:54 PM
 */

import com.patrickstar.blog.dao.UserDao;
import com.patrickstar.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String getUserPass(String userName) {
        return userDao.getUserPass(userName);
    }
}
