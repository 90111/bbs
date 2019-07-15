package com.bbs.api.Admin;

import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.Post.Impl.DistrictInfoServiceImpl;
import com.bbs.service.Post.Impl.PlateInfoServiceImpl;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import com.bbs.service.User.Impl.UserCollectionInfoServiceImpl;
import com.bbs.service.User.Impl.UserLikeInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import com.bbs.service.User.UserLoginInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

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

    @Autowired
    private PostTitleInfoDao postTitleInfoDao;


    @RequestMapping(value = "/searchByColum", method = RequestMethod.GET)
    public Map searchPost(String colum_name, String s, String nick_name, int page, int size) {
        System.out.println("调用searchByColum方法");
        if (s.isEmpty() || s == null || s.equals("")) {
            return null;
        }
        if (nick_name == null) nick_name="";
        Map<String, Object> map = new HashMap<>();
        try {
            PageInfo pageObj  = postInfoService.getPostTitleInfosByColum(colum_name, s, nick_name, page, size);
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

    @RequestMapping(value = "/deletePosts", method = RequestMethod.POST)
    public Map delete(@RequestBody List<PostTitleInfo> ls) {
        System.out.println("调用deletePosts");
        Map<String, Object> map = new HashMap<>();
        try{
            postInfoService.batchDelete(ls);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

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
            ArrayList<Map<String, Object>> array = new ArrayList<Map<String, Object>>();
            PageInfo pageObj1 = userLoginInfoService.getUserLoginInfos(page);
            PageInfo pageObj2 = userBaseInfoService.getUserBaseInfos(page);
            List<Map<String, Object>> ls1 = pageObj1.getList();
            List<Map<String, Object>> ls2 = pageObj2.getList();
            map.put("code", "200");
            map.put("LoginInfo", ls1);
            map.put("BaseInfo", ls2);
            map.put("num", pageObj1.getTotal());
        }catch (Exception e){
            map.put("code", "500");
        }
        return map;
    }
}
