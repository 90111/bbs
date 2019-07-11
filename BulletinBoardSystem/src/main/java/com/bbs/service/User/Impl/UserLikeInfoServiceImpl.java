package com.bbs.service.User.Impl;

import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.dao.User.UserLikeInfoDao;
import com.bbs.service.User.UserLikeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLikeInfoServiceImpl implements UserLikeInfoService {

    @Resource
    private UserLikeInfoDao userLikeInfoDao;

    @Resource
    private PostTitleInfoDao postTitleInfoDao;

    @Override
    public boolean checkIsLike(int user_id, int post_title_id) throws Exception {
        if(userLikeInfoDao.checkIsLike(user_id, post_title_id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public void changeLike(int user_id, int post_title_id) throws Exception {
        if (userLikeInfoDao.checkIsLike(user_id, post_title_id) == 1){
            userLikeInfoDao.deleteLike(user_id, post_title_id);
        }else {
            userLikeInfoDao.addLike(user_id, post_title_id);
        }
        postTitleInfoDao.updatePostTitleLikeNum(post_title_id);
    }


}
