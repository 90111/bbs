package com.bbs.api.Announcement;

import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import com.bbs.service.Announcement.lmpl.AnnouncementInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import org.springframework.web.bind.annotation.*;
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


    @RequestMapping(value = "/deteleAnnouncement", method = RequestMethod.POST)
    public Map deteleAnnouncement(int id){
        System.out.println("删除公告");
        Map<String,Object> map=new HashMap<>();
        try {
            announcementInfoService.deleteAnnouncementInfoById(id);
            map.put("code",200);
            map.put("msg", "删除公告成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "删除公告失败");
        }
        return map;
    }

    @RequestMapping(value = "/addAnnouncement",method = RequestMethod.POST)
    public Map addAnnouncement(@RequestBody AnnouncementInfo announcementInfo){
        System.out.println("添加公告");
        Map<String,Object> map=new HashMap<>();
        try {
            announcementInfoService.addAnnouncementInfo(announcementInfo);
            map.put("code",200);
            map.put("msg", "添加公告成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "添加公告失败");
        }
        return map;
    }

}
