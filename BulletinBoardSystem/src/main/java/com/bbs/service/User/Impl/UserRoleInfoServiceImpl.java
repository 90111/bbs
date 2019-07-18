package com.bbs.service.User.Impl;

import com.bbs.dao.User.RoleUserInfoDao;
import com.bbs.model.User.RoleUserInfo;
import com.bbs.service.User.UserRoleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserRoleInfoServiceImpl implements UserRoleInfoService {


    @Resource
    private RoleUserInfoDao roleUserInfoDao;

    @Override
    public void changeUserRole(int role, int user_info_id) throws Exception {
        roleUserInfoDao.changeUserRole(role, user_info_id);
    }
}
