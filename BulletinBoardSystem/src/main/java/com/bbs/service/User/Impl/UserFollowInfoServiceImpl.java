package com.bbs.service.User.Impl;

import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.dao.User.UserFollowDao;
import com.bbs.model.User.UserFollowInfo;
import com.bbs.service.User.UserFollowInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserFollowInfoServiceImpl implements UserFollowInfoService {

    @Resource
    private UserFollowDao userFollowDao;

    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Override
    public boolean chekIsFollowed(int user_id, int follow_id) throws Exception {
        UserFollowInfo info = userFollowDao.chekIsFollowed(user_id, follow_id);
        if (info != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void changeFollowed(int user_id, int follow_id) throws Exception {
        UserFollowInfo info = userFollowDao.chekIsFollowed(user_id, follow_id);
        if (info != null){
            userFollowDao.deleteFollowed(user_id, follow_id);
        }else {
            userFollowDao.addFollowed(user_id, follow_id);
        }
        userBaseInfoDao.updateUserFansNum(follow_id);
        userBaseInfoDao.updateUserFollowNum(user_id);
    }
}
