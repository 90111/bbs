package com.bbs.api;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    public String exp(HttpServletRequest request, Exception ex) {
        request.setAttribute("ex", ex);
        // 根据不同错误转向不同页面
        if(ex instanceof UnauthenticatedException) {
            return "token错误";
        }else if(ex instanceof UnauthorizedException) {
            return "用户无权限";
        } else {
            return "error";
        }
    }
}
