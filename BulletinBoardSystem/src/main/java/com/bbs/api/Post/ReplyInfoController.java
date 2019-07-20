package com.bbs.api.Post;


import com.bbs.model.Message.MessageInfo;
import com.bbs.model.Post.ReplyInfo;
import com.bbs.service.Message.Impl.MessageInfoInfoServiceImpl;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.bbs.service.Post.Impl.ReplyInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReplyInfoController {

    @Autowired
    private ReplyInfoServiceImpl replyInfoService;

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @Autowired
    private MessageInfoInfoServiceImpl messageInfoInfoService;

    @Autowired
    private PostTitleInfoServiceImpl postTitleInfoService;

    @RequestMapping(value = "/replyInfo/viewReplyInfo", method = RequestMethod.GET)
    public Map<String, Object> viewReplyInfo(int post_title_id) {
//        System.out.println("调用viewReplyInfo方法");
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("ReplyInfos", replyInfoService.getReplyInfos(post_title_id));
            map.put("code", "200");
            map.put("msg", "获取帖子回复信息成功");
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "获取帖子回复信息失败");
        }
        return map;
    }

    @RequiresAuthentication
    @RequestMapping(value = "replyInfo/addReplyInfo", method = RequestMethod.POST)
    public Map addReplyInfo(@RequestBody ReplyInfo replyInfo) {
//        System.out.println("调用addReplyInfo方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            try {
                int user_id = userLoginInfoService.getUserLoginInfoByName((String) currentUser.getPrincipal()).getId();
                replyInfo.setUser_id(user_id);
                replyInfoService.addReplyInfoService(replyInfo);
                MessageInfo info = new MessageInfo();
                info.setSend_user_id(user_id);
                info.setReceive_user_id(postTitleInfoService.getPostTitleById(replyInfo.getPost_title_id()).getOwner());
                StringBuilder sb = new StringBuilder();
                sb.append(replyInfo.getContent()+">"+replyInfo.getPost_title_id());
                info.setContent(sb.toString());
                info.setType(11);
                messageInfoInfoService.addMessage(info);
                map.put("code", "200");
                map.put("msg", "回复成功");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", "500");
                map.put("msg", "回复失败");
            }
        } else {
            map.put("code", "500");
            map.put("msg", "用户未登录");
        }
        return map;
    }
}
