package com.bbs.api.Message;


import com.bbs.service.Message.Impl.MessageInfoInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageInfoInfoServiceImpl messageInfoInfoService;

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @RequiresAuthentication
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public Map getMessage(int type, int page, int size) {
        System.out.println("调用getMessage方法");
        Subject currentUser = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>();
        try {
            int user_id = userLoginInfoService.getUserLoginInfoByName((String) currentUser.getPrincipal()).getId();
            PageInfo pageObj = messageInfoInfoService.getMessageInfos(user_id, type, page, size);
            List<Map<String, Object>> ls = pageObj.getList();
            map.put("data", ls);
            map.put("code", "200");
            map.put("msg", "获取信息成功");
            messageInfoInfoService.readMessageByType(user_id, type);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "获取信息失败");
        }
        return map;
    }


    @RequiresAuthentication
    @RequestMapping(value = "/getMessageNum", method = RequestMethod.GET)
    public Map getMessageNum() {
        System.out.println("调用getMessageNum方法");
        Subject currentUser = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>();
        try {
            int user_id = userLoginInfoService.getUserLoginInfoByName((String) currentUser.getPrincipal()).getId();
            Map <String, Object> data = new HashMap<>();
            data.put("total", messageInfoInfoService.getMessageNum(user_id));
            data.put("system", messageInfoInfoService.getMessageNumByType(user_id, 9));
            data.put("post", messageInfoInfoService.getMessageNumByType(user_id, 10));
            data.put("reply", messageInfoInfoService.getMessageNumByType(user_id, 11));
            map.put("data", data);
            map.put("code", "200");
            map.put("msg", "获取信息成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "获取信息失败");
        }
        return map;
    }


}
