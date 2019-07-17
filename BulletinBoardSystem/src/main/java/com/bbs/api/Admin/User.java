package com.bbs.api.Admin;

import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresAuthentication
@RequiresRoles({"admin", "moderator","district_owner"})
@RestController
@RequestMapping("/admin")
public class User {

    @Autowired
    private UserBaseInfoServiceImpl userBaseInfoService;

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;



    @RequestMapping(value = "/deleteUsers", method = RequestMethod.POST)
    public Map deleteUsers(@RequestBody List<UserLoginInfo> ls) {
        System.out.println("调用deleteUsers");
        Map<String, Object> map = new HashMap<>();
        try{
            userLoginInfoService.deleteUserLoginInfoById(ls);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }


    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public Map getUsers(int page){
        System.out.println("调用getUsers方法");
        Map<String, Object> map = new HashMap<>();
        try{
            PageInfo pageObj1 = userLoginInfoService.getUserLoginInfos(page);
            PageInfo pageObj2 = userBaseInfoService.getUserBaseInfos(page);
            List<Map<String, Object>> ls1 = pageObj1.getList();
            List<Map<String, Object>> ls2 = pageObj2.getList();
            map.put("code", "200");
            map.put("LoginInfo", ls1);
            map.put("BaseInfo", ls2);
            map.put("num", pageObj1.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
        }
        return map;
    }

    @RequestMapping(value = "/changeUserState", method = RequestMethod.POST)
    public Map changeUserState(int user_id, String colum_name, int state){
        System.out.println("调用changeUserState方法");
        Map<String, Object> map = new HashMap<>();
        try {
            userLoginInfoService.changeUserState(user_id, colum_name, state);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.GET)
    public Map searchUser(String colum_name, String s){
        System.out.println("调用searchUser方法");
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("ls", userLoginInfoService.searchUser(colum_name, s));
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }




}
