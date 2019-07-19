package com.bbs.api.Admin;

import com.bbs.model.Post.PlateInfo;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.Post.Impl.PlateInfoServiceImpl;
import com.bbs.service.User.Impl.DistrictModeratorInfoServiceImpl;
import com.bbs.service.User.Impl.UserBaseInfoServiceImpl;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import com.bbs.service.User.Impl.UserRoleInfoServiceImpl;
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RequiresAuthentication
@RequiresRoles(value = {"admin", "moderator"}, logical= Logical.OR)
@RestController
@RequestMapping("/admin")
public class Plate {

    @Autowired
    private PlateInfoServiceImpl plateInfoService;

    @Autowired
    private DistrictModeratorInfoServiceImpl districtModeratorInfoService;

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @Autowired
    private UserRoleInfoServiceImpl userRoleInfoService;

    @Autowired
    private UserBaseInfoServiceImpl userBaseInfoService;

    @RequestMapping(value = "/getPlates", method = RequestMethod.GET)
    public Map getPlates() {
        System.out.println("调用getPlates方法");
        Map<String, Object> map = new HashMap<>();
        try{
            Subject currentUser = SecurityUtils.getSubject();
            if(currentUser.hasRole("admin")){
                map.put("data", plateInfoService.getPlates());
            }else{
                UserLoginInfo userLoginInfo = userLoginInfoService.getUserLoginInfoByName((String) currentUser.getPrincipal());
                List<DistrictModeratorInfo> ls = districtModeratorInfoService.getDisModerInfosById("user_id", userLoginInfo.getId());
                map.put("data", plateInfoService.getPlatesById(ls));
            }
            map.put("code", "200");
            map.put("msg", "获取数据成功");
        }catch (Exception e){
            map.put("code", "500");
            map.put("msg", "获取数据失败");
        }
        return map;
    }

    @RequiresPermissions("createPlate")
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


    @RequiresPermissions("updatePlate")
    @RequestMapping(value = "/updatePlates",method = RequestMethod.POST)
    public Map updatePlates(int id, String name){
//        System.out.println("修改板块");
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

    @RequiresPermissions("deletePlate")
    @RequestMapping(value = "/deletePlate",method = RequestMethod.POST)
    public Map deletePlates(int plate_id){
//        System.out.println("删除板块");
        Map<String,Object> map=new HashMap<>();
        try {
            List<DistrictModeratorInfo> ls1 = districtModeratorInfoService.getDisModerInfosById("plate_id", plate_id);
            districtModeratorInfoService.deleteInfoByPlateId(plate_id);
            for (DistrictModeratorInfo i:ls1){
                int role = 1;
                List<DistrictModeratorInfo> ls2 = districtModeratorInfoService.getDisModerInfosById("user_id",i.getUser_id());
                if (ls2.size()!=0) role = 2;
                for (DistrictModeratorInfo info : ls2){
                    if (info.getDistrict_id() == -1){
                        role = 3;
                        break;
                    }
                }
                userRoleInfoService.changeUserRole(role, i.getUser_id());
            }
            plateInfoService.deletePlateInfoById(plate_id);
            map.put("code",200);
            map.put("msg", "删除板块成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "删除板块失败");
        }
        return map;
    }

    @RequiresPermissions("addModerator")
    @RequestMapping(value = "/addPlateModerator", method = RequestMethod.POST)
    public Map addPlateModerator(@RequestBody DistrictModeratorInfo districtModeratorInfo){
//        System.out.println("调用addPlateModerator方法");
        Map<String, Object> map = new HashMap<>();
        try {
            List<DistrictModeratorInfo> info = districtModeratorInfoService.getDisModerInfosById("plate_id", districtModeratorInfo.getPlate_id());
            for (DistrictModeratorInfo i : info){
                if (i.getUser_id() == districtModeratorInfo.getUser_id()){
                    map.put("code", "500");
                    map.put("msg", "该用户已是该版版主");
                    return map;
                }
            }
            districtModeratorInfoService.addModerInfo(districtModeratorInfo, 3);
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
//        System.out.println("调用getPlateModerator方法");
        Map<String, Object> map = new HashMap<>();
        try {
            List<DistrictModeratorInfo> ls = districtModeratorInfoService.getInfo("plate_id", plate_id);
            List<UserBaseInfo> ls2 = new LinkedList<>();
            for (DistrictModeratorInfo info : ls){
                UserBaseInfo user = userBaseInfoService.getUserBaseInfoByUserId(info.getUser_id());
                ls2.add(user);
            }
            map.put("ls", ls2);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequiresPermissions("deleteModerator")
    @RequestMapping(value = "/deletePlateModerator", method = RequestMethod.GET)
    public Map deletePlateInfo(int user_id, int plate_id){
//        System.out.println("调用deletePlateModerator方法");
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
