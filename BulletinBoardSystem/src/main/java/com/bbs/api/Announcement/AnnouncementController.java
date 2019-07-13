package com.bbs.api.Announcement;

import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import com.bbs.service.Announcement.lmpl.AnnouncementInfoServiceImpl;
import com.bbs.service.Post.Impl.DistrictInfoServiceImpl;
import com.bbs.service.Post.Impl.PlateInfoServiceImpl;
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

    @Autowired
    private DistrictInfoServiceImpl districtInfoService;

    @Autowired
    private PlateInfoServiceImpl plateInfoService;

    @RequestMapping(value = "/getRecentAnnouncement", method = RequestMethod.GET)
    public Map getTopAnnouncement(int plate_id, int district_id){
        System.out.println("获取最新公告");
        Map<String, Object> map = new HashMap<>();
        try{
            AnnouncementInfo announcementInfo = announcementInfoService.getRecentAnnouncement(plate_id, district_id);
            map.put("code","200");
            map.put("msg", "获取最新公告成功");
            map.put("topAnnouncement",announcementInfo);
            if(announcementInfo != null){
                map.put("userInfo", userBaseInfoService.getUserBaseInfoByUserId(announcementInfo.getOwner()));
                int districtId = -1;
                districtId = announcementInfo.getDistrict_id();
                int plateId = -1;
                plateId = announcementInfo.getPlate_id();
                if (districtId != -1){
                    map.put("district_name", districtInfoService.getgetDistrictInfo(districtId).getDistrict_name());
                }
                if(plateId != -1){
                    map.put("plate_name", plateInfoService.getPlateInfo(plate_id).getPlate_name());
                }
            }
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
