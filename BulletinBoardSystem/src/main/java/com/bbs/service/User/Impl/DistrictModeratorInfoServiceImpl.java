package com.bbs.service.User.Impl;


import com.bbs.dao.User.DistrictModeratorInfoDao;
import com.bbs.dao.User.RoleUserInfoDao;
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
    private RoleUserInfoDao roleUserInfoDao;

    @Resource
    private UserBaseInfoDao userBaseInfoDao;


    @Override
    public void addInfo(DistrictModeratorInfo districtModeratorInfo, int role) throws Exception {
        districtModeratorInfoDao.addInfo(districtModeratorInfo);
        if (role > roleUserInfoDao.getRoleUserInfo(districtModeratorInfo.getUser_id()).getRole_info_id())
            roleUserInfoDao.changeUserRole(role, districtModeratorInfo.getUser_id());
    }

    @Override
    public void deleteInfo(String colum_name, int user_id, int id) throws Exception {
        districtModeratorInfoDao.deleteInfo(colum_name,user_id, id);
        int role = 1;
        List<DistrictModeratorInfo> ls = districtModeratorInfoDao.getDisModerInfos(user_id);
        if (ls != null) role = 2;
        for (DistrictModeratorInfo info : ls){
            if (info.getDistrict_id() == -1){
                role = 3;
                break;
            }
        }
        roleUserInfoDao.changeUserRole(role, user_id);
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
