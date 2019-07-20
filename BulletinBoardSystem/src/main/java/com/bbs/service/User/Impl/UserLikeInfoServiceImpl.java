package com.bbs.service.User.Impl;

import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.dao.User.UserLikeInfoDao;
import com.bbs.service.User.UserLikeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserLikeInfoServiceImpl implements UserLikeInfoService {

    @Resource
    private UserLikeInfoDao userLikeInfoDao;

    @Resource
    private PostTitleInfoDao postTitleInfoDao;

    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Override
    public boolean checkIsLike(int user_id, int post_title_id) throws Exception {
        if(userLikeInfoDao.checkIsLike(user_id, post_title_id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean changeLike(int user_id, int post_title_id) throws Exception {
        boolean flag = false;
        if (userLikeInfoDao.checkIsLike(user_id, post_title_id) == 1){
            userLikeInfoDao.deleteLike(user_id, post_title_id);
        }else {
            userLikeInfoDao.addLike(user_id, post_title_id, new Date());
            flag = true;
        }
        postTitleInfoDao.updatePostTitleLikeNum(post_title_id);
        return flag;
    }

    @Override
    public int selectLikeNowNum() throws Exception {
        return userLikeInfoDao.selectLikeNowNum();
    }


}
