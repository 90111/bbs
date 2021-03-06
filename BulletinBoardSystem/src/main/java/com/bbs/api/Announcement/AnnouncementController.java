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
                int districtId = 0;
                districtId = announcementInfo.getDistrict_id();
                int plateId = 0;
                plateId = announcementInfo.getPlate_id();
                if (districtId != 0){
                    map.put("district_name", districtInfoService.getDistrictInfo(districtId).getDistrict_name());
                }
                if(plateId != 0){
                    map.put("plate_name", plateInfoService.getPlateInfo(plate_id).getPlate_name());
                }
            }
        }catch (Exception e){
            map.put("code","500");
            map.put("msg", "获取最新公告失败");
        }
        return map;
    }




}
