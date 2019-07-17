package com.bbs.api.Admin;

import com.bbs.model.Post.PlateInfo;
import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.service.Post.Impl.PlateInfoServiceImpl;
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
@RequiresRoles({"admin", "moderator","district_owner"})
@RestController
@RequestMapping("/admin")
public class Plate {

    @Autowired
    private PlateInfoServiceImpl plateInfoService;

    @Autowired
    private DistrictModeratorInfoServiceImpl districtModeratorInfoService;


    @RequestMapping(value = "/addPlates",method = RequestMethod.POST)
    public Map addPlates(String name){
        System.out.println("增加板块");
        Map<String,Object> map=new HashMap<>();
        try {
            PlateInfo info = plateInfoService.getPlateInfoByName(name);
            if (info != null){
                map.put("code",500);
                map.put("msg", "该板块已存在");
            }else {
                plateInfoService.addPlateInfoByName(name);
                map.put("code",200);
                map.put("msg", "增加板块成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg", "增加板块失败");
        }
        return map;
    }

    @RequestMapping(value = "/updatePlates",method = RequestMethod.POST)
    public Map updatePlates(int id, String name){
        System.out.println("修改板块");
        Map<String,Object> map=new HashMap<>();
        try {
            PlateInfo info = plateInfoService.getPlateInfoByName(name);
            if (info == null){
                plateInfoService.updatePlateInfo(id, name);
                map.put("code",200);
                map.put("msg", "修改板块成功");
            }else{
                map.put("code",500);
                map.put("msg", "修改失败，该板块已存在");
            }
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "修改板块失败");
        }
        return map;
    }

    @RequestMapping(value = "/deletePlate",method = RequestMethod.POST)
    public Map deletePlates(int plate_id){
        System.out.println("删除板块");
        Map<String,Object> map=new HashMap<>();
        try {
            plateInfoService.deletePlateInfoById(plate_id);
            map.put("code",200);
            map.put("msg", "删除板块成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "删除板块失败");
        }
        return map;
    }

    @RequestMapping(value = "/addPlateModerator", method = RequestMethod.POST)
    private Map addPlateModerator(@RequestBody DistrictModeratorInfo districtModeratorInfo){
        System.out.println("调用addPlateModerator方法");
        Map<String, Object> map = new HashMap<>();
        try {
            districtModeratorInfoService.addInfo(districtModeratorInfo, 3);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (DataIntegrityViolationException ex){
            map.put("code", "500");
            map.put("msg", "添加的id不存在");
        } catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/getPlateModerator", method = RequestMethod.GET)
    public Map getPlateInfo(int plate_id){
        System.out.println("调用getPlateModerator方法");
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("ls", districtModeratorInfoService.getInfo(plate_id, -1));
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/deletePlateModerator", method = RequestMethod.GET)
    public Map deletePlateInfo(int user_id, int plate_id){
        System.out.println("调用deletePlateModerator方法");
        Map<String, Object> map = new HashMap<>();
        try {
           districtModeratorInfoService.deleteInfo("plate_id", user_id, plate_id);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

}
