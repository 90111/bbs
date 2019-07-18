package com.bbs.api.Admin;

import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.Post.Impl.ReplyInfoServiceImpl;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @RequestMapping(value = "/getReplyNowNum",method = RequestMethod.GET)
    public Map getReplyNowNum(){
        System.out.println("今日回复数");
        Map<String,Object> map=new HashMap<>();
        try {
            int num=replyInfoService.selectReplyNowNum();
            map.put("获取今日回复数",num);
            map.put("code",200);
            map.put("msg", "成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg", "失败");
        }
        return map;
    }

    @RequestMapping(value = "/getPostNowNum",method = RequestMethod.GET)
    public Map getPostNowNum(){
        System.out.println("今日发帖数");
        Map<String,Object> map=new HashMap<>();
        try {
            int num=postTitleInfoService.selectPostNowNum();
            map.put("获取今日发帖数",num);
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
