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
    public void addModerInfo(DistrictModeratorInfo districtModeratorInfo, int role) throws Exception {
        districtModeratorInfoDao.addModerInfo(districtModeratorInfo);
        if (role > roleUserInfoDao.getRoleUserInfo(districtModeratorInfo.getUser_id()).getRole_info_id())
            roleUserInfoDao.changeUserRole(role, districtModeratorInfo.getUser_id());
    }

    @Override
    public void addDisInfo(DistrictModeratorInfo districtModeratorInfo, int role) throws Exception {
        districtModeratorInfoDao.addDisInfo(districtModeratorInfo);
        if (role > roleUserInfoDao.getRoleUserInfo(districtModeratorInfo.getUser_id()).getRole_info_id())
            roleUserInfoDao.changeUserRole(role, districtModeratorInfo.getUser_id());
    }

    @Override
    public void deleteInfo(String colum_name, int user_id, int id) throws Exception {
        districtModeratorInfoDao.deleteInfo(colum_name,user_id, id);
        int role = 1;
        List<DistrictModeratorInfo> ls = districtModeratorInfoDao.getDisModerInfosById("user_id", user_id);
        if (ls.size() != 0) role = 2;
        for (DistrictModeratorInfo info : ls){
            if (info.getDistrict_id() == 0){
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
    public List<DistrictModeratorInfo> getInfo(String colum_name, int district_id) throws Exception {
        List<DistrictModeratorInfo> ls = null;
        if (colum_name.equals("plate_id")){
            ls = districtModeratorInfoDao.getModeratorInfo(colum_name, district_id);
        }else{
            ls = districtModeratorInfoDao.getDisInfo(colum_name, district_id);
        }
        return ls;
    }

    @Override
    public List<DistrictModeratorInfo> getDisModerInfosById(String colum_name, int user_id) throws Exception {
        return districtModeratorInfoDao.getDisModerInfosById(colum_name, user_id);
    }

    @Override
    public List<DistrictModeratorInfo> getUserDis(int user_id) throws Exception {
        return districtModeratorInfoDao.getUserDis(user_id);
    }

    @Override
    public List<DistrictModeratorInfo> getModeratorInfo(String colum_name, int plate_id) throws Exception {
        return districtModeratorInfoDao.getModeratorInfo(colum_name, plate_id);
    }

    @Override
    public void deleteInfoByPlateId(int plate_id) throws Exception {
        districtModeratorInfoDao.deleteInfoByPlateId(plate_id);
    }
}
