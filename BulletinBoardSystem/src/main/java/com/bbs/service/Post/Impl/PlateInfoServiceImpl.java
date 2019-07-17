package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.DistrictInfoDao;
import com.bbs.dao.Post.PlateInfoDao;
import com.bbs.dao.User.DistrictModeratorInfoDao;
import com.bbs.model.Post.DistrictInfo;
import com.bbs.model.Post.PlateInfo;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.service.Post.PlateInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlateInfoServiceImpl implements PlateInfoService {

    @Resource
    private PlateInfoDao plateInfoDao;

    @Resource
    private DistrictInfoDao districtInfoDao;

    @Resource
    private DistrictModeratorInfoDao districtModeratorInfoDao;

    @Override
    public List<PlateInfo> getPlates() throws Exception {
        List<PlateInfo> ls = plateInfoDao.getPlates();
        for (PlateInfo p : ls) {
            p.setDistrictInfos(districtInfoDao.getDistricts(p.getId()));
        }
        return ls;
    }

    @Override
    public PlateInfo getPlateInfo(int plate_id) throws Exception {
        return plateInfoDao.getPlateInfo(plate_id);
    }

    @Override
    public PlateInfo getPlateInfoByName(String name) throws Exception {
        return plateInfoDao.getPlateInfoByName(name);
    }

    @Override
    public void addPlateInfoByName(String name) throws Exception {
        plateInfoDao.addPlateInfoByName(name);

    }

    @Override
    public void updatePlateInfo(int id, String name) throws Exception {
        plateInfoDao.updatePlateInfo(id, name);
    }

    @Override
    public void deletePlateInfoById(int id) throws Exception {
        districtModeratorInfoDao.updateUserRole(id);
        districtModeratorInfoDao.deleteInfo2("plate_id", id);
        plateInfoDao.deletePlateInfoById(id);
    }

}
