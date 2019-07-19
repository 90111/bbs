package com.bbs.api.Admin;

import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.model.Post.DistrictInfo;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.Post.Impl.DistrictInfoServiceImpl;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.User.Impl.DistrictModeratorInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RequiresAuthentication
@RequiresRoles(value = {"admin", "moderator", "district_owner"}, logical = Logical.OR)
@RestController
@RequestMapping("/admin")
public class Post {

    @Autowired
    private PostTitleInfoServiceImpl postInfoService;

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @Autowired
    private DistrictModeratorInfoServiceImpl districtModeratorInfoService;

    @Autowired
    private DistrictInfoServiceImpl districtInfoService;

    @Autowired
    private PostTitleInfoDao postTitleInfoDao;

    @RequiresRoles("admin")
    @RequestMapping(value = "/getPostTitles", method = RequestMethod.GET)
    public Map getPostTitles(int page, int size) {
//        System.out.println("调用getPostTitles方法");
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("data", postInfoService.getPostTitleInfosByTime("post_time", page, size));
            map.put("code", "200");
            map.put("msg", "获取数据成功");
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "获取数据失败");
        }
        return map;
    }

    @RequestMapping(value = "/getPostTitlesByDis", method = RequestMethod.GET)
    public Map getPostTitlesByDis(int district_id, int page, int size) {
//        System.out.println("调用getPostTitlesByDis方法");
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("data", postInfoService.getPostTitleInfos(district_id, "post_time", page, size, 1));
            map.put("code", "200");
            map.put("msg", "获取数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "获取数据失败");
        }
        return map;
    }


    /*
    模糊查询
     */
    @RequestMapping(value = "/searchByColum", method = RequestMethod.GET)
    public Map searchPost(String colum_name, String s, String nick_name, int page, int size) {
//        System.out.println("调用searchByColum方法");
        if (s.isEmpty() || s == null || s.equals("")) {
            return null;
        }
        if (nick_name == null) nick_name = "";
        Map<String, Object> map = new HashMap<>();
        try {
            Subject currentUser = SecurityUtils.getSubject();
            PageInfo pageObj = new PageInfo();
            if (currentUser.hasRole("admin")){
                pageObj = postInfoService.getPostTitleInfosByColum(colum_name, s, nick_name, page, size, "");
            }else {
                String username = (String) currentUser.getPrincipal();
                List<DistrictModeratorInfo> ls = districtModeratorInfoService.getDisModerInfosById("user_id", userLoginInfoService.getUserLoginInfoByName(username).getId());
                StringBuilder sb = new StringBuilder();
                for (DistrictModeratorInfo info : ls) {
                    if (info.getDistrict_id() == 0) {
                        List<DistrictInfo> ls3 = districtInfoService.getDistrictInfos(info.getPlate_id());
                        for (DistrictInfo i : ls3) {
                            sb.append("'").append(i.getId()).append("'").append(",");
                        }
                    } else {
                        sb.append("'").append(info.getDistrict_id()).append("'").append(",");
                    }
                }
                sb.deleteCharAt(sb.length()-1);
                pageObj = postInfoService.getPostTitleInfosByColum(colum_name, s, nick_name, page, size, sb.toString());
            }
            List<Map<String, Object>> ls = pageObj.getList();
            if (ls == null) {
                return null;
            }
            map.put("code", "200");
            map.put("msg", "操作成功");
            map.put("ls", ls);
            map.put("num", pageObj.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequiresPermissions("changePostState")
    @RequestMapping(value = "/changePostState", method = RequestMethod.POST)
    public Map changePostState(int post_id, int state, String colum_name) {
//        System.out.println("调用changePostState方法");
        Map<String, Object> map = new HashMap<>();
        try {
            postInfoService.changePostState(post_id, colum_name, state);
            map.put("code", "200");
            map.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/changePostDis", method = RequestMethod.POST)
    public Map changePostDis(int post_id, int dis_id, String colum_name) {
//        System.out.println("调用changePostDis方法");
        Map<String, Object> map = new HashMap<>();
        try {
            postInfoService.changePostDis(post_id, colum_name, dis_id);
            map.put("code", "200");
            map.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequiresPermissions("deletePost")
    @RequestMapping(value = "/deletePosts", method = RequestMethod.POST)
    public Map delete(@RequestBody List<PostTitleInfo> ls) {
//        System.out.println("调用deletePosts");
        Map<String, Object> map = new HashMap<>();
        try {
            postInfoService.batchDelete(ls);
            map.put("code", "200");
            map.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }
}
