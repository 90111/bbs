package com.bbs.api.Post;

import com.bbs.model.Post.DistrictInfo;
import com.bbs.service.Post.Impl.DistrictInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plate/district")
public class DistrictController {

    @Autowired
    private DistrictInfoServiceImpl districtInfoServiceImpl;

    @RequestMapping(value = "/getDistricts", method = RequestMethod.GET)
    public List<DistrictInfo> getDistricts(int plate_id) throws Exception{
        System.out.println("获取分区信息");
        return districtInfoServiceImpl.getDistrictInfos(plate_id);
    }

    @RequestMapping(value = "/addDistricts",method = RequestMethod.POST)
    public Map addDistricts(int plate_id,String district_name){
        System.out.println("增加分区");
        Map<String,Object> map=new HashMap<>();
        try {
            districtInfoServiceImpl.addDistrictInfo(plate_id, district_name);
            map.put("code",200);
            map.put("msg", "增加分区成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "增加分区失败");
        }
        return map;
    }

    @RequestMapping(value = "/updateDistricts",method = RequestMethod.POST)
    public Map updateDistricts(@RequestBody DistrictInfo districtInfo){
        System.out.println("修改分区");
        Map<String,Object> map=new HashMap<>();
        try {
            districtInfoServiceImpl.updateDistrictInfo(districtInfo);
            map.put("code",200);
            map.put("msg", "修改分区成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "修改分区失败");
        }
        return map;
    }

    @RequestMapping(value = "/deleteDistricts",method = RequestMethod.POST)
    public Map deleteDistricts(int id){
        System.out.println("删除分区");
        Map<String,Object> map=new HashMap<>();
        try {
            districtInfoServiceImpl.deleteDistrictInfo(id);
            map.put("code",200);
            map.put("msg", "删除分区成功");
        }catch (Exception e){
            map.put("code",500);
            map.put("msg", "删除分区失败");
        }
        return map;
    }
}
