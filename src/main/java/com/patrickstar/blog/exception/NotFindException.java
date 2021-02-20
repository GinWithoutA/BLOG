package com.patrickstar.blog.exception;/*
 * @PackageName: com.patrickstar.blog.exception
 * @ClassName: NotFindException
 * @Description:博客消失无法查看的错误
 * @Author: PatrickStaR
 * @Date: 10/30/2019 12:37 PM
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFindException extends RuntimeException {
    public NotFindException() {
    }

    public NotFindException(String message) {
        super(message);
    }

    public NotFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
