package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.DistrictInfoDao;
import com.bbs.dao.Post.PlateInfoDao;
import com.bbs.model.Post.PlateInfo;
import com.bbs.service.Post.PlateInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlateInfoServiceImpl implements PlateInfoService {

    @Resource
    private PlateInfoDao plateInfoDao;

    @Resource
    DistrictInfoDao districtInfoDao;

    @Override
    public List<PlateInfo> getPlates() throws Exception {
        List<PlateInfo> ls = plateInfoDao.getPlates();
        for (PlateInfo p : ls){
            p.setDistrictInfos(districtInfoDao.getDistricts(p.getId()));
        }
        return ls;
    }
}