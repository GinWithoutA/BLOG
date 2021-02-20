package com.patrickstar.blog;

import com.patrickstar.blog.entity.Blog;
import com.patrickstar.blog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private BlogService blogService;

    @Test
    void contextLoads() {
        String s = "A Good   Day";
        for(String ss : s.split(" ")){
            System.out.print(ss + ",");
        }
        System.out.println();


    }




}
