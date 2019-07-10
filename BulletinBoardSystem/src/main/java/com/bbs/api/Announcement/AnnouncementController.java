package com.bbs.api.Announcement;

import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import com.bbs.service.Announcement.lmpl.AnnouncementInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/anon")
@RestController
public class AnnouncementController {
    @Autowired
    private AnnouncementInfoServiceImpl announcementInfoService;

    @Autowired
    private UserBaseInfoServiceImpl userBaseInfoService;

    @RequestMapping(value = "/getTopAnnouncement", method = RequestMethod.GET)
    public Map getTopAnnouncement(){
        System.out.println("获取最新公告");
        Map<String, Object> map = new HashMap<>();
        try{
            AnnouncementInfo announcementInfo = announcementInfoService.getTopAnnouncementInfo();
            map.put("code","200");
            map.put("msg", "获取最新公告成功");
            map.put("topAnnouncement",announcementInfo);
            map.put("userInfo", userBaseInfoService.getUserBaseInfoByUserId(announcementInfo.getOwner()));
        }catch (Exception e){
            map.put("code","500");
            map.put("msg", "获取最新公告失败");
        }
        return map;
    }

}
