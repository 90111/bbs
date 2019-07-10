package com.bbs.api.Post;


import com.bbs.model.Post.ReplyInfo;
import com.bbs.service.Post.Impl.ReplyInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReplyInfoController {

    @Autowired
    private ReplyInfoServiceImpl replyInfoService;

    @RequestMapping(value = "/anon/replyInfo/viewReplyInfo", method = RequestMethod.GET)
    public Map<String, Object> viewReplyInfo(int post_title_id){
        System.out.println("调用viewReplyInfo方法");
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("ReplyInfos", replyInfoService.getReplyInfos(post_title_id));
            map.put("code", "200");
            map.put("msg", "获取帖子回复信息成功");
        }catch (Exception e){
            map.put("code", "500");
            map.put("msg", "获取帖子回复信息失败");
        }
        return map;
    }


    @RequestMapping(value = "replyInfo/addReplyInfo", method = RequestMethod.POST)
    public Map addReplyInfo(@RequestBody ReplyInfo replyInfo) {
        System.out.println("调用addReplyInfo方法");
        Map<String, Object> map = new HashMap<>();
        try{
            replyInfoService.addReplyInfoService(replyInfo);
            map.put("code", "200");
            map.put("msg", "回复成功");
        }catch (Exception e){
            map.put("code", "500");
            map.put("msg", "回复失败");
        }
        return map;
    }
}
