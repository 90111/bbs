package com.bbs.service.User.Impl;

import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.User.UserBaseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserBaseInfoServiceImpl implements UserBaseInfoService {

    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Override
    public UserBaseInfo getUserBaseInfoByUserId(int user_id) throws Exception {
        return userBaseInfoDao.getUserBaseInfoByUserId(user_id);
    }

    @Override
    public void updateUserLikeNum(int user_id) throws Exception {
        userBaseInfoDao.updateUserLikeNum(user_id);
    }

    @Override
    public void updateUserBaseInfo(UserBaseInfo userBaseInfo) throws Exception {
        userBaseInfoDao.updateUserBaseInfo(userBaseInfo);
    }

    @Override
    public List<UserBaseInfo> getFollowList(int id) throws Exception {
        return userBaseInfoDao.getFollowList(id);
    }

    @Override
    public List<UserBaseInfo> getFansList(int id) throws Exception {
        return userBaseInfoDao.getFansList(id);
    }
}
