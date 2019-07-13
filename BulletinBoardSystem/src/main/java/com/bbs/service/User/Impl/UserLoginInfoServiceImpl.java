package com.bbs.service.User.Impl;


import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.dao.User.UserLoginInfoDao;
import com.bbs.model.User.RoleInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.User.UserLoginInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLoginInfoServiceImpl implements UserLoginInfoService {

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
        userBaseInfoDao.addUserBaseInfo(userLoginInfo.getId(), userLoginInfo.getUser_name());
    }

    @Override
    public void deleteUserLoginInfoById(int id) throws Exception {
        userLoginInfoDao.deleteUserLoginInfoById(id);
        userBaseInfoDao.deleteUserBaseInfoById(id);
    }

    @Override
    public UserLoginInfo getUserLoginInfoByMail(String mail) throws Exception {
        UserLoginInfo userLoginInfo = userLoginInfoDao.getUserLoginInfoByMail(mail);
        if (userLoginInfo == null){
            return null;
        }
        return userLoginInfo;
    }

    @Override
    public void updateUserPwd(int id, String newPwd) throws Exception {
        userLoginInfoDao.updateUserPwd(id, newPwd);
    }


}
