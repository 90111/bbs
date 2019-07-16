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


}
