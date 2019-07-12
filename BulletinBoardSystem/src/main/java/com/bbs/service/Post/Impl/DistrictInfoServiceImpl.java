package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.DistrictInfoDao;
import com.bbs.dao.Post.PlateInfoDao;
import com.bbs.model.Post.DistrictInfo;
import com.bbs.model.Post.PlateInfo;
import com.bbs.service.Post.DistrictInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DistrictInfoServiceImpl implements DistrictInfoService {

    @Resource
    private DistrictInfoDao districtInfoDao;

    @Override
    public List<DistrictInfo> getDistrictInfos(int plate_id) throws Exception {
        return districtInfoDao.getDistricts(plate_id);
    }

    @Override
    public DistrictInfo getgetDistrictInfo(int district_id) throws Exception {
        return districtInfoDao.getDistrictInfo(district_id);
    }
    @Override
    public void addDistrictInfo(int id,String name) throws Exception{
        districtInfoDao.addDistrictInfo(id,name);
    }

    @Override
    public void updateDistrictInfo(DistrictInfo districtInfo) throws Exception{
        districtInfoDao.updateDistrictInfo(districtInfo);
    }

    @Override
    public void deleteDistrictInfo(int id) throws Exception{
        districtInfoDao.deleteDistrictInfo(id);
    }
}
