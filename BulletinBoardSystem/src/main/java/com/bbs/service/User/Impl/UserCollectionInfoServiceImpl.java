package com.bbs.service.User.Impl;

import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.dao.User.UserCollectionInfoDao;
import com.bbs.service.User.UserCollectionInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserCollectionInfoServiceImpl implements UserCollectionInfoService {

    @Resource
    private UserCollectionInfoDao userCollectionInfoDao;

    @Resource
    private PostTitleInfoDao postTitleInfoDao;

    @Override
    public boolean checkIsCollected(int user_id, int post_title_id) throws Exception {
        if(userCollectionInfoDao.checkIsCollected(user_id, post_title_id) == 1)
            return true;
        else return false;
    }

    @Override
    public void changeCollection(int user_id, int post_title_id) throws Exception {
        if (userCollectionInfoDao.checkIsCollected(user_id, post_title_id) == 1){
            userCollectionInfoDao.deleteCollection(user_id, post_title_id);
        }else {
            userCollectionInfoDao.addCollection(user_id, post_title_id);
        }
        postTitleInfoDao.updatePostTitleRecommendNum(post_title_id);
    }
}
