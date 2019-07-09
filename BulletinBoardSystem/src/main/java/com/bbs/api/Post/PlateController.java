package com.bbs.api.Post;

import com.bbs.model.Post.PlateInfo;
import com.bbs.service.Post.Impl.PlateInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anon/plate")
public class PlateController {

    @Autowired
    private PlateInfoServiceImpl plateInfoServiceImpl;

    @RequestMapping(value = "/getPlates", method = RequestMethod.GET)
    public List<PlateInfo> getPlates() throws Exception{
        System.out.println("获取板块分区信息");
        return plateInfoServiceImpl.getPlates();
    }
}
