package com.bbs.api.User;


import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserCollectionInfo;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.User.Impl.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
public class UserBaseInfoController {

    @Autowired
    private UserBaseInfoServiceImpl baseInfoService;

    @Autowired
    private PostTitleInfoServiceImpl postTitleInfoService;

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @Autowired
    private UserLikeInfoServiceImpl userLikeInfoService;

    @Autowired
    private UserCollectionInfoServiceImpl userCollectionInfoService;

    @Autowired
    private UserFollowInfoServiceImpl userFollowInfoService;

    /*
    参数id为user_id
     */
    @RequestMapping(value = "/baseInfo", method = RequestMethod.POST)
    public Map getBaseInfo(int id) {
        System.out.println("调用getBaseInfo方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (currentUser.isAuthenticated()){
                int user_id = userLoginInfoService.getUserLoginInfoByName((String)currentUser.getPrincipal()).getId();
                map.put("isFollowed", userFollowInfoService.chekIsFollowed(user_id, id));
            }
            map.put("code", "200");
            map.put("msg", "个人信息获取成功");
            map.put("userInfo", baseInfoService.getUserBaseInfoByUserId(id));
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "个人信息获取失败");
        }
        return map;
    }

    @RequestMapping(value = "/updateBaseInfo", method = RequestMethod.POST)
    public Map updateBaseInfo(@RequestBody UserBaseInfo userBaseInfo) {
        System.out.println("调用updateBaseInfo方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        try {
            int user_id = userLoginInfoService.getUserLoginInfoByName((String) currentUser.getPrincipal()).getId();
            userBaseInfo.setUser_id(user_id);
            baseInfoService.updateUserBaseInfo(userBaseInfo);
            map.put("code", "200");
            map.put("msg", "修改个人信息成功");
        }catch (Exception e){
            map.put("code", "500");
            map.put("msg", "修改个人信息失败");
        }
        return map;
    }


    @RequestMapping(value = "/userPostTitles", method = RequestMethod.POST)
    public Map getUserPostTitle(int id) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("调用getUserPostTitles方法");
        Subject currentUser = SecurityUtils.getSubject();
        try {
            List<PostTitleInfo> ls = postTitleInfoService.getUserPostTitleByUserId(id);
            if (currentUser.isAuthenticated()){
                for (PostTitleInfo info : ls){
                    info.setLiked(userLikeInfoService.checkIsLike(id, info.getId()));
                    info.setCollected(userCollectionInfoService.checkIsCollected(id, info.getId()));
                }
            }
            map.put("code", "200");
            map.put("msg", "个人帖子获取成功");
            map.put("recentPost", ls);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "个人帖子获取失败");
        }
        return map;
    }

    @RequestMapping(value = "/UserCollection", method = RequestMethod.POST)
    public Map getUserCollection(int id) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("调用getUserCollection方法");
        Subject currentUser = SecurityUtils.getSubject();
        try {
            List<UserCollectionInfo> ls = postTitleInfoService.getUserCollection(id);
            if (currentUser.isAuthenticated()){
                for (UserCollectionInfo info : ls){
                    info.setLiked(userLikeInfoService.checkIsLike(id, info.getPost_title_id()));
                    info.setCollected(userCollectionInfoService.checkIsCollected(id, info.getPost_title_id()));
                }
            }
            map.put("code", "200");
            map.put("msg", "个人收藏获取成功");
            map.put("collection", ls);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "个人收藏获取失败");
        }
        return map;
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.POST)
    public Map deletePostTitleById(int post_title_id) throws Exception {
        System.out.println("调用deletePostTitleById方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();
        int user_id = -1;
        user_id = userLoginInfoService.getUserLoginInfoByName(username).getId();
        Integer user_id2 = postTitleInfoService.getPostTitleById(post_title_id).getOwner();
        if (user_id2.equals(user_id)){
            try {
                map.put("code", "200");
                map.put("msg", "帖子删除成功");
                postTitleInfoService.deleteByPostTitleId(user_id, post_title_id);
                return map;
            }catch (Exception e){
                map.put("code", "500");
                map.put("msg", "帖子删除失败");
                e.printStackTrace();
                return map;
            }
        }
        map.put("code", "500");
        map.put("msg", "该用户没有权限");

        return map;
    }


    @RequestMapping(value = "/followed", method = RequestMethod.GET)
    public Map followed(int follow_id) {
        System.out.println("调用followed方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (currentUser.isAuthenticated()){
                int user_id = userLoginInfoService.getUserLoginInfoByName((String)currentUser.getPrincipal()).getId();
                userFollowInfoService.changeFollowed(user_id, follow_id);
                map.put("code", "200");
            }else {
                map.put("code", "500");
                map.put("msg", "未登录");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "调用followed方法出错");
        }
        return map;
    }

    @RequestMapping(value = "/getFollowList", method = RequestMethod.GET)
    public Map getFollowList() {
        System.out.println("调用getFollowList方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if(currentUser.isAuthenticated()){
                int user_id = userLoginInfoService.getUserLoginInfoByName((String)currentUser.getPrincipal()).getId();
                List<UserBaseInfo> ls = baseInfoService.getFollowList(user_id);
                if (ls == null) return null;
                map.put("ls", ls);
                map.put("code", "200");
                map.put("msg", "操作成功");
            }
        }catch (Exception e){
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

}
