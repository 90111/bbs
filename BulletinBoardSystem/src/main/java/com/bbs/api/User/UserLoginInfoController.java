package com.bbs.api.User;


import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserLoginInfoController extends BaseController{

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @Autowired
    private UserBaseInfoServiceImpl userBaseInfoService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Serializable login(@RequestBody UserLoginInfo userLoginInfo) throws JSONException {
        System.out.println("调用login方法");
        JSONObject jsonObject = new JSONObject();
        //添加用户认证信息
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginInfo.getUser_name(),
                userLoginInfo.getPassword());
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            UserLoginInfo info = userLoginInfoService.getUserLoginInfoByName(userLoginInfo.getUser_name());
            currentUser.login(usernamePasswordToken);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("code", "200");
            jsonObject.put("id", info.getId());
            jsonObject.put("user_name", info.getUser_name());
            jsonObject.put("icon", userBaseInfoService.getUserBaseInfoByUserId(info.getId()).getIcon());
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map register(@RequestBody UserLoginInfo userLoginInfo) throws Exception {
        System.out.println("调用register方法");
        userLoginInfoService.addUserLoginInfo(userLoginInfo);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "注册成功");
        return map;
    }

    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
    public Serializable checkUsername(@RequestBody UserLoginInfo userLoginInfo) throws Exception {
        System.out.println("调用checkUsername方法");
        JSONObject jsonObject = new JSONObject();
        UserLoginInfo userLoginInfo1 = null;
        userLoginInfo1 = userLoginInfoService.getUserLoginInfoByName(userLoginInfo.getUser_name());
        if (userLoginInfo1 == null){
            jsonObject.put("code", "200");
            jsonObject.put("msg", "该用户名可用");
        }else{
            jsonObject.put("code", "500");
            jsonObject.put("msg", "用户名不可用，已存在该用户名");
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/checkMail", method = RequestMethod.POST)
    public Serializable checkMail(@RequestBody UserLoginInfo userLoginInfo) throws Exception {
        System.out.println("调用checkMail方法");
        JSONObject jsonObject = new JSONObject();
        UserLoginInfo userLoginInfo1 = null;
        userLoginInfo1 = userLoginInfoService.getUserLoginInfoByMail(userLoginInfo.getMail());
        if (userLoginInfo1 == null){
            jsonObject.put("code", "200");
            jsonObject.put("msg", "该邮箱未被注册");
        }else{
            jsonObject.put("code", "500");
            jsonObject.put("msg", "该邮箱已被注册");
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Map logout() {
        System.out.println("调用logout方法");
        Map<String, Object> map = new HashMap<>();
        try{
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            map.put("code", "200");
            map.put("msg", "退出成功");
        }catch (Exception e){
            map.put("code", "500");
            map.put("msg", "退出失败");
        }
        return map;
    }


    @RequestMapping(value = "/checkSession", method = RequestMethod.POST)
    public Map checkSession() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()){
            String username = currentUser.getPrincipal().toString();
            int id = userLoginInfoService.getUserLoginInfoByName(username).getId();
            map.put("code", "200");
            map.put("msg", "已登录");
            map.put("id", id);
        }else{
            map.put("code", "500");
            map.put("msg", "未登录");
        }
        return map;
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.GET)
    public Map changePwd() {
        Subject currentUser = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>();
        try{
            if(currentUser.isAuthenticated()) {
                UserLoginInfo info = userLoginInfoService.getUserLoginInfoByName((String) currentUser.getPrincipal());
                map.put("code", "200");
                map.put("msg", "请求成功");
                map.put("userInfo", info);
            }else{
                map.put("code", "500");
                map.put("msg", "用户未登录");
            }
        }catch (Exception e){
            map.put("code", "500");
                map.put("msg", "请求失败");
        }
        return map;
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public Map changePwd(String oldPwd, String newPwd) {
        System.out.println("调用changePwd方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        try{
            if(currentUser.isAuthenticated()){
                UserLoginInfo info = userLoginInfoService.getUserLoginInfoByName((String) currentUser.getPrincipal());
                if (info.getPassword().equals(oldPwd)){
                    userLoginInfoService.updateUserPwd(info.getId(), newPwd);
                }
                map.put("code", "200");
                map.put("msg", "修改密码成功");
            }else{
                map.put("code", "500");
                map.put("msg", "用户未登录");
            }
        }catch (Exception e){
            map.put("code", "500");
            map.put("msg", "修改密码失败");
        }
        return map;
    }
}
