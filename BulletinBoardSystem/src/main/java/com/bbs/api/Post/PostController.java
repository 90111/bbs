package com.bbs.api.Post;

import com.bbs.model.Post.DistrictInfo;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.service.Post.DistrictInfoService;
import com.bbs.service.Post.Impl.DistrictInfoServiceImpl;
import com.bbs.service.Post.Impl.PlateInfoServiceImpl;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.User.Impl.UserCollectionInfoServiceImpl;
import com.bbs.service.User.Impl.UserLikeInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/anon/post")
public class PostController {

    @Autowired
    private PostTitleInfoServiceImpl postInfoService;

    @Autowired
    private UserBaseInfoServiceImpl userBaseInfoService;

    @Autowired
    private UserLikeInfoServiceImpl userLikeInfoService;

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @Autowired
    private UserCollectionInfoServiceImpl userCollectionInfoService;

    @Autowired
    private DistrictInfoServiceImpl districtInfoService;

    @Autowired
    private PlateInfoServiceImpl plateInfoService;


    @RequestMapping(value = "/getPostTitles", method = RequestMethod.GET)
    public Map getPostTitlesByDistrictId(int id, String orderby, int page, int size) {
        System.out.println("调用getPostTitlesByDistrictId方法");
        Map<String, Object> map = new HashMap<>();
        try {
            PageInfo pageObj = postInfoService.getPostTitleInfos(id, orderby, page, size);
            List<Map<String, Object>> ls = pageObj.getList();
            map.put("code", "200");
            map.put("msg", "获取分区帖子成功");
            map.put("postInfos", ls);
            map.put("num", pageObj.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "获取分区帖子失败");
        }
        return map;
    }

    /*
    传入帖子id号
     */
    @RequestMapping(value = "/getPostTitleContent", method = RequestMethod.GET)
    public Map getPostTitleContent(int id) throws Exception {
        System.out.println("调用getPostTitleContent");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        int user_id = -1;
        if (currentUser.isAuthenticated()) {
            String username = (String) currentUser.getPrincipal();
            user_id = userLoginInfoService.getUserLoginInfoByName(username).getId();
        }
        try {
            PostTitleInfo info = postInfoService.getPostTitleContent(id);
            DistrictInfo disInfo = districtInfoService.getDistrictInfo(info.getDistrictInfo_id());
            if (user_id != -1) {
                info.setCollected(userCollectionInfoService.checkIsCollected(user_id, id));
                info.setLiked(userLikeInfoService.checkIsLike(user_id, id));
            }
            map.put("code", "200");
            map.put("msg", "获取帖子内容成功");
            map.put("Content", info);
            map.put("UserInfo", userBaseInfoService.getUserBaseInfoByUserId(info.getOwner()));
            map.put("CurrentPostTitle", postInfoService.getUserRecentPostTitleByUserId(info.getOwner()));
            map.put("disInfo", disInfo);
            map.put("plateInfo", plateInfoService.getPlateInfo(disInfo.getPlate_id()));
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "获取帖子内容失败");
        }
        return map;
    }


    @RequestMapping(value = "/getIndexPostTitles", method = RequestMethod.GET)
    public Map getIndexPostTitles(int page, int size) {
        System.out.println("调用getIndexPostTitles方法");
        Map<String, Object> map = new HashMap<>();
        try {
            PageInfo pageObj = postInfoService.getPostTitleInfosByTime("post_time", page, size);
            List<Map<String, Object>> ls = pageObj.getList();

            map.put("code", "200");
            map.put("msg", "获取首页帖子成功");
            map.put("PostTitleList", ls);
            map.put("num", pageObj.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "获取首页帖子失败");
        }
        return map;
    }


    @RequiresAuthentication
    @RequestMapping(value = "/addPostTitle", method = RequestMethod.POST)
    public Map addPostTitleInfo(@RequestBody PostTitleInfo postTitleInfo) {
        System.out.println("调用addPostTitleInfo方法");
        Map<String, Object> map = new HashMap();
        Subject current = SecurityUtils.getSubject();
        try {
            int user_id = userLoginInfoService.getUserLoginInfoByName((String) current.getPrincipal()).getId();
            postTitleInfo.setOwner(user_id);
            postInfoService.addPostTitleInfo(postTitleInfo);
            map.put("code", "200");
            map.put("msg", "发布帖子成功");
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "发布帖子失败");
        }
        return map;
    }


    @RequiresAuthentication
    @RequestMapping(value = "/updatePostTitle", method = RequestMethod.POST)
    public Map updatePostTItleInfo(@RequestBody PostTitleInfo postTitleInfo) {
        System.out.println("调用updatePostTItleInfo方法");
        Map<String, Object> map = new HashMap();
        Subject current = SecurityUtils.getSubject();
        try {
            int user_id = userLoginInfoService.getUserLoginInfoByName((String) current.getPrincipal()).getId();
            PostTitleInfo info = postInfoService.getPostTitleById(postTitleInfo.getId());
            if (info.getOwner() == user_id) {
                postInfoService.updatePostTitleInfo(postTitleInfo);
                map.put("code", "200");
                map.put("msg", "修改帖子成功");
            }else{
                map.put("code", "200");
                map.put("msg", "您不是当前帖子所有人，修改失败");
            }
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "修改帖子失败");
        }
        return map;
    }

    @RequiresAuthentication
    @RequestMapping(value = "/like", method = RequestMethod.GET)
    public Map addLike(int post_title_id) throws Exception {
        System.out.println("调用addLike方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();
        int user_id = userLoginInfoService.getUserLoginInfoByName(username).getId();
        try {
            userLikeInfoService.changeLike(user_id, post_title_id);
            userBaseInfoService.updateUserLikeNum(postInfoService.getPostTitleById(post_title_id).getOwner());
            map.put("code", "200");
            map.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequiresAuthentication
    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    public Map addCollect(int post_title_id) throws Exception {
        System.out.println("调用addCollection");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();
        int user_id = userLoginInfoService.getUserLoginInfoByName(username).getId();
        try {
            userCollectionInfoService.changeCollection(user_id, post_title_id);
            map.put("code", "200");
            map.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/getGreatPostTitles", method = RequestMethod.GET)
    public Map getGreatPostTitles() {
        System.out.println("调用getGreatPostTitles方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        String date_1 = dateFormat.format(date1);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -7);
        Date date2 = c.getTime();
        String date_2 = dateFormat.format(date2);
        long diff = -1;
        try {
            List<PostTitleInfo> ls = postInfoService.getPostTitleBetweenTime(date_2, date_1);
            ArrayList<Map<String, Object>> array = new ArrayList<Map<String, Object>>();
            if (currentUser.isAuthenticated()) {
                int user_id = userLoginInfoService.getUserLoginInfoByName((String) currentUser.getPrincipal()).getId();
                for (PostTitleInfo info : ls) {
                    info.setLiked(userLikeInfoService.checkIsLike(user_id, info.getId()));
                    info.setCollected(userCollectionInfoService.checkIsCollected(user_id, info.getId()));
                }
            }
            for (PostTitleInfo info : ls) {
                Map<String, Object> map2 = new HashMap<>();
                Date post_time = info.getPost_time();
                dateFormat.format(post_time);
                diff = post_time.getTime() - date1.getTime() + (date1.getTime() - date2.getTime());
                diff /= 1000000;
                diff += info.getView_num() * 5 + info.getReply_num() * 7 + info.getLike_num() * 9 + info.getRecommend_num() * 10;
                map2.put("postTitle", info);
                map2.put("diff", diff);
                array.add(map2);
            }
            Collections.sort(array, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    Integer o1Value = Integer.parseInt(o1.get("diff").toString());
                    Integer o2Value = Integer.parseInt(o2.get("diff").toString());
                    return o2Value.compareTo(o1Value);
                }
            });
            map.put("code", "200");
            map.put("msg", "获取精选帖子列表成功");
            map.put("postInfos", array);
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "获取精选帖子列表失败");
        }
        return map;
    }


    @RequestMapping(value = "/getRecommendPostTitles", method = RequestMethod.GET)
    public Map getRecommendPostTitles(int district_id) {
        System.out.println("调用getRecommendPostTitles方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        try {
            List<PostTitleInfo> ls = postInfoService.getRecommendPostTitles(district_id);
            if (currentUser.isAuthenticated()) {
                int user_id = userLoginInfoService.getUserLoginInfoByName((String) currentUser.getPrincipal()).getId();
                for (PostTitleInfo info : ls) {
                    info.setLiked(userLikeInfoService.checkIsLike(user_id, info.getId()));
                    info.setCollected(userCollectionInfoService.checkIsCollected(user_id, info.getId()));
                }
            }
            map.put("recomdTitles", ls);
            map.put("code", "200");
            map.put("msg", "获取分区精品帖成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "获取分区精品帖失败");
        }
        return map;
    }


    @RequestMapping(value = "/searchPost", method = RequestMethod.GET)
    public Map searchPost(String postTitle) {
        System.out.println("调用searchPost方法");
        if (postTitle.isEmpty() || postTitle == null || postTitle.equals("")) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            List<PostTitleInfo> ls = postInfoService.searchPost(postTitle);
            if (ls == null) {
                return null;
            }
            map.put("code", "200");
            map.put("msg", "操作成功");
            map.put("ls", ls);
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

}
