package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.UserLikeInfoDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLikeInfoServiceImpl implements UserLikeInfoService {

    @Resource
    private UserLikeInfoDao userLikeInfoDao;

    @Override
    public boolean checkIsLike(int user_id, int post_title_id) throws Exception {
        if(userLikeInfoDao.checkIsLike(user_id, post_title_id) == 1)
            return true;
        else
            return false;
    }
}
