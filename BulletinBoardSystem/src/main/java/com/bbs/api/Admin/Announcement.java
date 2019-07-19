package com.bbs.api.Admin;

import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import com.bbs.service.Announcement.lmpl.AnnouncementInfoServiceImpl;
import com.bbs.service.Post.Impl.PlateInfoServiceImpl;
import com.bbs.service.User.Impl.DistrictModeratorInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiresAuthentication
@RequiresRoles(value = {"admin", "moderator","district_owner"}, logical= Logical.OR)
@RequestMapping("/admin")
public class Announcement {

    @Autowired
    private AnnouncementInfoServiceImpl announcementInfoService;

    @RequiresRoles("admin")
    @RequestMapping(value = "/getAnnouncements", method = RequestMethod.GET)
    public Map getAnnouncements(int page, int size){
        System.out.println("调用getAnnouncements方法");
        Map<String, Object> map = new HashMap<>();
        try{
            PageInfo pageObj = announcementInfoService.getAnnounceInfos(page, size);
            List<Map<String, Object>> ls = pageObj.getList();
            map.put("data", ls);
            map.put("code", "200");
            map.put("msg", "获取成功");
            map.put("num", pageObj.getTotal());
        }catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "获取失败");
        }
        return map;
    }

    @RequestMapping(value = "/getAnnByDis", method = RequestMethod.GET)
    public Map getPostTitlesByDis(int plate_id, int district_id, int page, int size) {
        System.out.println("调用getAnnByDis方法");
        Map<String, Object> map = new HashMap<>();
        try {
            PageInfo pageObj = new PageInfo();
            if (plate_id == 0 & district_id == 0){
                pageObj = announcementInfoService.getAnnInfos(page, size);
            }else if (plate_id == 0){
                pageObj = announcementInfoService.getAnnByColumName("district_id", district_id, page, size);
            }else {
                pageObj = announcementInfoService.getAnnByColumName("plate_id", plate_id, page, size);
            }
            List<Map<String, Object>> ls = pageObj.getList();
            map.put("data", ls);
            map.put("code", "200");
            map.put("msg", "获取数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "获取数据失败");
        }
        return map;
    }


    @RequestMapping(value = "/searchAnn", method = RequestMethod.POST)
    public Map searchAnnByPlate(int page, int size) {
        System.out.println("调用searchAnnByPlate方法");
        Map<String, Object> map = new HashMap<>();
        try{
            PageInfo pageObj = announcementInfoService.getAnnounceInfos(-1, page, size);
            List<Map<String, Object>> ls2 = pageObj.getList();
            Map<String, Object> m = new HashMap<>();
            map.put("data", ls2);
            map.put("code", "200");
            map.put("msg", "查询成功");
            map.put("num", pageObj.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "查询失败");
        }

        return map;
    }

    @RequestMapping(value = "/searchAnnByPlate", method = RequestMethod.POST)
    public Map searchAnnByPlate(int plate_id, int page, int size) {
        System.out.println("调用searchAnnByPlate方法");
        Map<String, Object> map = new HashMap<>();
        try{
            PageInfo pageObj = announcementInfoService.getAnnounceInfos(plate_id, page, size);
            List<Map<String, Object>> ls2 = pageObj.getList();
            Map<String, Object> m = new HashMap<>();
            map.put("data", ls2);
            map.put("code", "200");
            map.put("msg", "查询成功");
            map.put("num", pageObj.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "查询失败");
        }

        return map;
    }

    @RequestMapping(value = "/searchAnnByDis", method = RequestMethod.POST)
    public Map searchAnnByDis(int dis_id, int page, int size) {
        System.out.println("调用searchAnnByDis方法");
        Map<String, Object> map = new HashMap<>();
        try{
            PageInfo pageObj = announcementInfoService.getAnnounceInfos2(dis_id, page, size);
            List<Map<String, Object>> ls2 = pageObj.getList();
            Map<String, Object> m = new HashMap<>();
            map.put("data", ls2);
            map.put("code", "200");
            map.put("msg", "查询成功");
            map.put("num", pageObj.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "查询失败");
        }

        return map;
    }

    @RequiresPermissions("deleteAnnouncement")
    @RequestMapping(value = "/deleteAnnouncement", method = RequestMethod.POST)
    public Map deteleAnnouncement(@RequestBody List<AnnouncementInfo> ls){
        System.out.println("删除公告");
        Map<String,Object> map=new HashMap<>();
        try {
            announcementInfoService.batchDelete(ls);
            map.put("code",200);
            map.put("msg", "删除公告成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "删除公告失败");
        }
        return map;
    }

    @RequiresPermissions("createAnnouncement")
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
