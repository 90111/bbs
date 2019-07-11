package com.bbs.api.Post;

import com.bbs.model.Post.PostTitleInfo;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/anon/post")
public class PostController {

    @Autowired
    private PostTitleInfoServiceImpl postInfoService;

    @Autowired
    private UserBaseInfoServiceImpl userBaseInfoService;

    @RequestMapping(value = "/getPostTitles", method = RequestMethod.GET)
    public List<PostTitleInfo> getPostTitlesByDistrictId(int id) throws Exception {
        System.out.println("调用getPostTitlesByDistrictId方法");

        return postInfoService.getPostTitleInfos(id);
    }

    @RequestMapping(value = "/getPostTitleContent", method = RequestMethod.GET)
    public Map getPostTitleContent(int id) {
        System.out.println("获取帖子内容");
        Map<String, Object> map = new HashMap<>();
        try {
            PostTitleInfo info = postInfoService.getPostTitleContent(id);
            map.put("code", "200");
            map.put("msg", "获取帖子内容成功");
            map.put("Content", info);
            map.put("UserInfo", userBaseInfoService.getUserBaseInfoByUserId(info.getOwner()));
            map.put("CurrentPostTitle", postInfoService.getUserRecentPostTitleByUserId(info.getOwner()));
        }catch (Exception e){
            map.put("code", "500");
            map.put("msg", "获取帖子内容失败");
        }
        return map;
    }

    @RequestMapping(value = "/getIndexPostTitles", method = RequestMethod.GET)
    public Map getIndexPostTitles(){
        System.out.println("调用getIndexPostTitles方法");
        Map<String, Object> map = new HashMap<>();
        try{
            String s = "post_time";
            List<PostTitleInfo> ls = postInfoService.getPostTitleInfosByTime(s);
            map.put("code", "200");
            map.put("msg", "获取首页帖子成功");
            map.put("PostTitleList", ls);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "获取首页帖子失败");
        }
        return map;
    }


    @RequestMapping(value = "/addPostTitle", method = RequestMethod.POST)
    @RequiresPermissions("createPost")
    public Map addPostTitleInfo(@RequestBody PostTitleInfo postTitleInfo) {
        System.out.println("调用addPostTitleInfo方法");
        Map<String, Object> map = new HashMap();
        try {
            postInfoService.addPostTitleInfo(postTitleInfo);
            map.put("code", "200");
            map.put("msg", "发布帖子成功");
        }catch (Exception e){
            map.put("code", "500");
            map.put("msg", "发布帖子失败");
        }
        return map;
    }
}
