package com.bbs.api.Admin;

import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.service.Post.Impl.DistrictInfoServiceImpl;
import com.bbs.service.Post.Impl.PlateInfoServiceImpl;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import com.bbs.service.User.Impl.UserCollectionInfoServiceImpl;
import com.bbs.service.User.Impl.UserLikeInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
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
    public Map searchPost(String colum_name, String s, String nick_name) {
        System.out.println("调用searchByColum方法");
        if (s.isEmpty() || s == null || s.equals("")) {
            return null;
        }
        if (nick_name == null) nick_name="";
        Map<String, Object> map = new HashMap<>();
        try {
            List<PostTitleInfo> ls = postInfoService.getPostTitleInfosByColum(colum_name, s, nick_name);
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map delete(@RequestBody Object arr) {
        System.out.println("调用delete");
        Map<String, Object> map = new HashMap<>();
        HashMap arrayList = (HashMap) arr;
        StringBuilder sb = new StringBuilder();
//        for (int i = 0;i < arrayList.size(); i++){
//            System.out.println(sb.append("'").append(arrayList.get(i)).append("'"));
//        }
        try{
            postTitleInfoDao.batchDelete(arrayList);
            map.put("code", "200");
        }catch (Exception e){
            map.put("code", "500");
        }
        return map;
    }
}
