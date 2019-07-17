package com.bbs.api.Admin;


import com.bbs.model.Post.DistrictInfo;
import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.service.Post.Impl.DistrictInfoServiceImpl;
import com.bbs.service.User.Impl.DistrictModeratorInfoServiceImpl;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiresAuthentication
@RequiresRoles("admin")
@RestController
@RequestMapping("/admin")
public class District {

    @Autowired
    private DistrictInfoServiceImpl districtInfoService;

    @Autowired
    private DistrictModeratorInfoServiceImpl districtModeratorInfoService;


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

    @RequestMapping(value = "/deleteDistricts", method = RequestMethod.POST)
    public Map deleteDistricts(int id) {
        System.out.println("删除分区");
        Map<String, Object> map = new HashMap<>();
        try {
            districtInfoService.deleteDistrictInfo(id);
            map.put("code", 200);
            map.put("msg", "删除分区成功");
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "删除分区失败");
        }
        return map;
    }

    @RequestMapping(value = "/addDisOwner", method = RequestMethod.POST)
    public Map addModerator(@RequestBody DistrictModeratorInfo districtModeratorInfo) {
        System.out.println("调用addDisOwner方法");
        Map<String, Object> map = new HashMap<>();
        try {
            districtModeratorInfoService.addInfo(districtModeratorInfo, 2);
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
    public Map getPlateInfo(int plate_id, int district_id) {
        System.out.println("调用getDisOwner方法");
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("ls", districtModeratorInfoService.getInfo(plate_id, district_id));
            map.put("code", "200");
            map.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

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
