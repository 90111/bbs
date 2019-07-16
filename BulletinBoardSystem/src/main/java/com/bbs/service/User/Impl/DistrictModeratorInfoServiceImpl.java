package com.bbs.service.User.Impl;


import com.bbs.dao.User.DistrictModeratorInfoDao;
import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.dao.User.UserLoginInfoDao;
import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.service.User.DistrictModeratorInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class DistrictModeratorInfoServiceImpl implements DistrictModeratorInfoService {

    @Resource
    private DistrictModeratorInfoDao districtModeratorInfoDao;

    @Resource
    private UserLoginInfoDao userLoginInfoDao;

    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Override
    public void addInfo(DistrictModeratorInfo districtModeratorInfo, int role) throws Exception {
        districtModeratorInfoDao.addInfo(districtModeratorInfo);
        userLoginInfoDao.getUserLoginInfoById(districtModeratorInfo.getUser_id()).setRole(role);
    }

    @Override
    public void deleteInfo(String colum_name, int s) throws Exception {
        districtModeratorInfoDao.deleteInfo(colum_name,s);
    }

    @Override
    public void updateInfo(DistrictModeratorInfo districtModeratorInfo) throws Exception {
        districtModeratorInfoDao.updateInfo(districtModeratorInfo);
    }

    @Override
    public List<UserBaseInfo> getInfo(int plate_id, int district_id) throws Exception {
        List<DistrictModeratorInfo> ls = districtModeratorInfoDao.getInfo(plate_id, district_id);
        List<UserBaseInfo> ls2 = new LinkedList<>();
        for (DistrictModeratorInfo info : ls){
            UserBaseInfo user = userBaseInfoDao.getUserBaseInfoByUserId(info.getUser_id());
            ls2.add(user);
        }
        return ls2;
    }
}
