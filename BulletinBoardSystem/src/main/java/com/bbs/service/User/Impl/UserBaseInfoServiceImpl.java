package com.bbs.service.User.Impl;

import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.User.UserBaseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserBaseInfoServiceImpl implements UserBaseInfoService {

    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Override
    public UserBaseInfo getUserBaseInfoByUserId(int user_id) throws Exception {
        return userBaseInfoDao.getUserBaseInfoByUserId(user_id);
    }
}
