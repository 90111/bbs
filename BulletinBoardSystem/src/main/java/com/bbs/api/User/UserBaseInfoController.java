package com.bbs.api.User;


import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
public class UserBaseInfoController {

    @Autowired
    private UserBaseInfoServiceImpl baseInfoService;

    @Autowired
    private PostTitleInfoServiceImpl postTitleInfoService;

    @RequestMapping(value = "/baseInfo", method = RequestMethod.POST)
    public Map getBaseInfo(int id) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("调用getBaseInfo方法");
        try {
            map.put("code", "200");
            map.put("msg", "个人信息获取成功");
            map.put("userInfo", baseInfoService.getUserBaseInfoByUserId(id));
            map.put("recentPost", postTitleInfoService.getUserPostTitleByUserId(id));
            map.put("collection", postTitleInfoService.getUserCollection(id));
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "200");
            map.put("msg", "个人信息获取失败");
        }
        return map;
    }
}
