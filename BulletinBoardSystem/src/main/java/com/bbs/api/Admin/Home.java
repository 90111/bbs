package com.bbs.api.Admin;

import com.bbs.dao.User.UserCollectionInfoDao;
import com.bbs.dao.User.UserLikeInfoDao;
import com.bbs.dao.User.UserLoginInfoDao;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.Post.Impl.ReplyInfoServiceImpl;
import com.bbs.service.User.Impl.UserCollectionInfoServiceImpl;
import com.bbs.service.User.Impl.UserLikeInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import com.bbs.service.User.UserLoginInfoService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiresAuthentication
@RequiresRoles({"admin"})
@RequestMapping("/admin")
public class Home {

    @Autowired
    private ReplyInfoServiceImpl replyInfoService;

    @Autowired
    private PostTitleInfoServiceImpl postTitleInfoService;

    @Autowired
    private UserCollectionInfoServiceImpl userCollectionInfoService;

    @Autowired
    private UserLikeInfoServiceImpl userLikeInfoService;

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @RequestMapping(value = "/getHomeMessage",method = RequestMethod.GET)
    public Map getReplyNowNum(){
        System.out.println("今日回复数");
        Map<String,Object> map=new HashMap<>();
        try {
            int replyNum=replyInfoService.selectReplyNowNum();
            int postNum=postTitleInfoService.selectPostNowNum();
            int likeNum=userLikeInfoService.selectLikeNowNum();
            int collectNum=userCollectionInfoService.selectCollectNowNum();
            List<UserLoginInfo> registNumList = userLoginInfoService.getAllRegist_time();
            Map<String, Object> m1 = new HashMap<>();
            m1.put("replyNum", replyNum);
            m1.put("postNum", postNum);
            m1.put("likeNum", likeNum);
            m1.put("collectNum", collectNum);
            m1.put("registNumList", registNumList);
            map.put("data",m1);
            map.put("code",200);
            map.put("msg", "成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg", "失败");
        }
        return map;
    }
}