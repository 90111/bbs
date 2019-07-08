package com.bbs.api;


import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.UserLoginInfoServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserLoginInfoController {

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Serializable login(@RequestBody UserLoginInfo userLoginInfo) throws JSONException {
        System.out.println("调用login方法");
        JSONObject jsonObject = new JSONObject();
        //添加用户认证信息
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginInfo.getUser_name(),
                userLoginInfo.getPassword());
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            currentUser.login(usernamePasswordToken);
            jsonObject.put("token", currentUser.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("login方法调用完毕");
        return jsonObject.toString();
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @RequiresPermissions("createPlate")
    public String getIndex(){
        Subject currentUser = SecurityUtils.getSubject();
        boolean i = currentUser.isPermitted("createPlate");
        System.out.println(i);
        return "ok";
    }

    @RequestMapping(value = "/logout")
    public String logout() throws JSONException {
        System.out.println("调用logout方法");
        JSONObject jsonObject = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        jsonObject.put("msg", "退出成功");
        return "login";
    }

    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }
}
