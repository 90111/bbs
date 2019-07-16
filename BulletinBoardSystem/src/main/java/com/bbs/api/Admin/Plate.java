package com.bbs.api.Admin;

import com.bbs.model.Post.PlateInfo;
import com.bbs.service.Post.Impl.PlateInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class Plate {

    @Autowired
    private PlateInfoServiceImpl plateInfoService;


    @RequestMapping(value = "/addPlates",method = RequestMethod.POST)
    public Map addPlates(String name){
        System.out.println("增加板块");
        Map<String,Object> map=new HashMap<>();
        try {
            plateInfoService.addPlateInfoByName(name);
            map.put("code",200);
            map.put("msg", "增加板块成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            map.put("msg", "增加板块失败");
        }
        return map;
    }

    @RequestMapping(value = "/updatePlates",method = RequestMethod.POST)
    public Map updatePlates(@RequestBody PlateInfo plateInfo){
        System.out.println("修改板块");
        Map<String,Object> map=new HashMap<>();
        try {
            plateInfoService.updatePlateInfo(plateInfo);
            map.put("code",200);
            map.put("msg", "修改板块成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "修改板块失败");
        }
        return map;
    }

    @RequestMapping(value = "/deletePlates",method = RequestMethod.POST)
    public Map deletePlates(int id){
        System.out.println("删除板块");
        Map<String,Object> map=new HashMap<>();
        try {
            plateInfoService.deletePlateInfoById(id);
            map.put("code",200);
            map.put("msg", "删除板块成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "删除板块失败");
        }
        return map;
    }

}
