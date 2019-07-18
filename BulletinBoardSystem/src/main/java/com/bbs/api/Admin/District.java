package com.bbs.api.Admin;


import com.bbs.model.Post.DistrictInfo;
import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.Post.Impl.DistrictInfoServiceImpl;
import com.bbs.service.User.Impl.DistrictModeratorInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequiresAuthentication
@RequiresRoles(value = {"admin", "moderator","district_owner"}, logical= Logical.OR)
@RestController
@RequestMapping("/admin")
public class District {

    @Autowired
    private DistrictInfoServiceImpl districtInfoService;

    @Autowired
    private DistrictModeratorInfoServiceImpl districtModeratorInfoService;

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @Autowired
    private UserBaseInfoServiceImpl userBaseInfoService;

    @RequestMapping(value = "/getDis", method = RequestMethod.GET)
    public Map getDis() {
        System.out.println("调用getDis方法");
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        String username = (String) currentUser.getPrincipal();
        try {
            List<DistrictModeratorInfo> ls = districtModeratorInfoService.getDisModerInfosById("user_id", userLoginInfoService.getUserLoginInfoByName(username).getId());
            List<DistrictInfo> ls2 = new LinkedList<>();
            for (DistrictModeratorInfo info : ls){
                if (info.getDistrict_id() == 0){
                    List<DistrictInfo> ls3 = districtInfoService.getDistrictInfos(info.getPlate_id());
                    for (DistrictInfo i : ls3){
                        ls2.add(districtInfoService.getDistrictInfo(i.getId()));
                    }
                }else{
                    ls2.add(districtInfoService.getDistrictInfo(info.getDistrict_id()));
                }
            }
            map.put("data", ls2);
            map.put("code", "200");
            map.put("msg", "获取分区信息成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "获取分区信息错误");
        }
        return map;
    }

    @RequiresPermissions("createDistrict")
    @RequestMapping(value = "/addDistricts", method = RequestMethod.POST)
    public Map addDistricts(int plate_id, String district_name) {
        System.out.println("增加分区");
        Map<String, Object> map = new HashMap<>();
        try {
            DistrictInfo info = districtInfoService.getDistrictByPlateAndName(plate_id, district_name);
            if (info != null){
                map.put("code", 500);
                map.put("msg", "该板块下已存在该分区");
            }else{
                districtInfoService.addDistrictInfo(plate_id, district_name);
                map.put("code", 200);
                map.put("msg", "增加分区成功");
            }
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "增加分区失败");
        }
        return map;
    }

    @RequiresPermissions("updateDistrict")
    @RequestMapping(value = "/updateDistricts", method = RequestMethod.POST)
    public Map updateDistricts(@RequestBody DistrictInfo districtInfo) {
        System.out.println("修改分区");
        Map<String, Object> map = new HashMap<>();
        try {
            DistrictInfo info = districtInfoService.getDistrictByPlateAndName(districtInfo.getPlate_id(), districtInfo.getDistrict_name());
            if (info==null){
                districtInfoService.updateDistrictInfo(districtInfo);
                map.put("code", 200);
                map.put("msg", "修改分区成功");
            }else {
                map.put("code", 500);
                map.put("msg", "修改失败，该板块下已有该分区");
            }
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "修改分区失败");
        }
        return map;
    }

    @RequiresPermissions("deleteDistrict")
    @RequestMapping(value = "/deleteDistricts", method = RequestMethod.POST)
    public Map deleteDistricts(int id) {
        System.out.println("删除分区");
        Map<String, Object> map = new HashMap<>();
        try {
            List<DistrictModeratorInfo> ls =districtModeratorInfoService.getInfo("district_id", id);
            List<UserBaseInfo> ls2 = new LinkedList<>();
            for (DistrictModeratorInfo info : ls){
                UserBaseInfo user = userBaseInfoService.getUserBaseInfoByUserId(info.getUser_id());
                ls2.add(user);
            }
            for (UserBaseInfo info : ls2){
                districtModeratorInfoService.deleteInfo("district_id", info.getUser_id(), id);
            }
            districtInfoService.deleteDistrictInfo(id);
            map.put("code", 200);
            map.put("msg", "删除分区成功");
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "删除分区失败");
        }
        return map;
    }

    @RequiresPermissions("addDisOwner")
    @RequestMapping(value = "/addDisOwner", method = RequestMethod.POST)
    public Map addModerator(@RequestBody DistrictModeratorInfo districtModeratorInfo) {
        System.out.println("调用addDisOwner方法");
        Map<String, Object> map = new HashMap<>();
        try {
            List<DistrictModeratorInfo> info = districtModeratorInfoService.getDisModerInfosById("district_id", districtModeratorInfo.getDistrict_id());
            if (info.size() != 0){
                map.put("code", "500");
                map.put("msg", "该用户已是该区区主");
                return map;
            }
            districtModeratorInfoService.addDisInfo(districtModeratorInfo, 2);
            map.put("code", "200");
            map.put("msg", "操作成功");
        } catch (DataIntegrityViolationException ex) {
            map.put("code", "500");
            map.put("msg", "添加的id不存在");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/getDisOwner", method = RequestMethod.GET)
    public Map getPlateInfo(int district_id) {
        System.out.println("调用getDisOwner方法");
        Map<String, Object> map = new HashMap<>();
        try {
            List<DistrictModeratorInfo> ls = districtModeratorInfoService.getInfo("district_id", district_id);
            List<UserBaseInfo> ls2 = new LinkedList<>();
            for (DistrictModeratorInfo info : ls){
                UserBaseInfo user = userBaseInfoService.getUserBaseInfoByUserId(info.getUser_id());
                ls2.add(user);
            }
            map.put("ls", ls2);
            map.put("code", "200");
            map.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequiresPermissions("deleteDisOwner")
    @RequestMapping(value = "/deleteDisOwner", method = RequestMethod.GET)
    public Map deletePlateInfo(int user_id, int district_id) {
        System.out.println("调用deleteDisOwner方法");
        Map<String, Object> map = new HashMap<>();
        try {
            districtModeratorInfoService.deleteInfo("district_id", user_id, district_id);
            map.put("code", "200");
            map.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }
}
