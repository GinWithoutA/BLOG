package com.patrickstar.blog.entity;/*
 * @PackageName: com.patrickstar.blog.entity
 * @ClassName: User
 * @Description:
 * @Author: PatrickStaR
 * @Date: 10/30/2019 3:46 PM
 */

/**
 * @author PatrickStaR
**/
public class User {
    /**
     * 用户序列号
    **/
    private Integer id;
    /**
     * 昵称
    **/
    private String nickName;
    /**
     * 密码
    **/
    private String password;

    public User(){

    }

    public User(Integer id, String nickName, String password) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
