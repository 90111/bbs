package com.bbs.api.User;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class BaseController {
    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    public Serializable exp(HttpServletRequest request, Exception ex) throws JSONException {
        request.setAttribute("ex", ex);
        JSONObject jsonObject = new JSONObject();
        // 根据不同错误转向不同页面
        if(ex instanceof UnauthenticatedException) {
            jsonObject.put("msg", "token错误");
            jsonObject.put("code", "10000");
            return jsonObject.toString();
        }else if(ex instanceof UnauthorizedException) {
            jsonObject.put("msg", "用户无权限");
            jsonObject.put("code", "10001");
            return jsonObject.toString();
        } else {
            return "error";
        }
    }
}
