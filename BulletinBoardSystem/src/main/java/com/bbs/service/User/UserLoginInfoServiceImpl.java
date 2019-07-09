package com.bbs.service.User;


import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.dao.User.UserLoginInfoDao;
import com.bbs.model.User.RoleInfo;
import com.bbs.model.User.UserLoginInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLoginInfoServiceImpl implements UserLoginInfoService{

    @Resource
    private UserLoginInfoDao userLoginInfoDao;
    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    public UserLoginInfo getUserLoginInfoByName(String username) throws Exception {
        UserLoginInfo userLoginInfo = userLoginInfoDao.getUserLoginInfoByName(username);
        if (userLoginInfo == null){
            return null;
        }
        userLoginInfo.setRoleInfos(userLoginInfoDao.LoadRolePermission(userLoginInfo.getId()));
        for (RoleInfo roleInfo : userLoginInfo.getRoleInfos()){
            roleInfo.setFunctionInfos(userLoginInfoDao.getFunctions(roleInfo.getId()));
        }
        return userLoginInfo;
    }

    @Override
    public void addUserLoginInfo(UserLoginInfo userLoginInfo) throws Exception {
        userLoginInfoDao.addUserLoginInfo(userLoginInfo);
        userBaseInfoDao.addUserBaseInfo(userLoginInfo.getId());
    }


}
