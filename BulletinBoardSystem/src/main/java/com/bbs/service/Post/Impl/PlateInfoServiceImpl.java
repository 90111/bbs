package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.DistrictInfoDao;
import com.bbs.dao.Post.PlateInfoDao;
import com.bbs.dao.User.DistrictModeratorInfoDao;
import com.bbs.dao.User.RoleUserInfoDao;
import com.bbs.model.Post.DistrictInfo;
import com.bbs.model.Post.PlateInfo;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.model.User.UserLoginInfo;
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

    @Resource
    private RoleUserInfoDao roleUserInfoDao;

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
    public void deletePlateInfoById(int plate_id) throws Exception {
        plateInfoDao.deletePlateInfoById(plate_id);
    }

    @Override
    public List<PlateInfo> getPlatesById(List<DistrictModeratorInfo> districtModeratorInfos) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (DistrictModeratorInfo info : districtModeratorInfos){
            if (info.getDistrict_id() == 0){
                sb.append("'").append(info.getPlate_id()).append("'").append(",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        String s = sb.toString();
        List<PlateInfo> ls = plateInfoDao.getPlatesById(s);
        for (PlateInfo info : ls){
            info.setDistrictInfos(districtInfoDao.getDistricts(info.getId()));
        }
        return ls;
    }

    @Override
    public int getPlateNum() throws Exception {
        return plateInfoDao.getPlateNum();
    }

}
