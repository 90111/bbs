package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.UserCollectionInfo;
import com.bbs.service.Post.PostTitleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class PostTitleInfoServiceImpl implements PostTitleInfoService {

    @Resource
    private PostTitleInfoDao postTitleInfoDao;

    @Override
    public List<PostTitleInfo> getPostTitleInfos(int id) throws Exception {
        List<PostTitleInfo> ls = postTitleInfoDao.getPostTitleInfos(id);

        return ls;
    }

    @Override
    public PostTitleInfo getPostTitleContent(int id) throws Exception {
        postTitleInfoDao.updatePostTitleViewNum(id);
        return postTitleInfoDao.getPostTitleContent(id);
    }

    @Override
    public List<PostTitleInfo> getPostTitleInfosByTime(String s) throws Exception {
        return postTitleInfoDao.getPostTitleInfosByTime(s);
    }

    @Override
    public List<PostTitleInfo> getUserRecentPostTitleByUserId(int id) throws Exception {
        return postTitleInfoDao.getUserRecentPostTitleByUserId(id);
    }

    @Override
    public void addPostTitleInfo(PostTitleInfo postTitleInfo) throws Exception {
        postTitleInfo.setPost_time(new Date());
        postTitleInfoDao.addPostTitleInfo(postTitleInfo);
    }

    @Override
    public List<PostTitleInfo> getUserPostTitleByUserId(int id) throws Exception {
        return postTitleInfoDao.getUserPostTitleByUserId(id);
    }

    @Override
    public List<UserCollectionInfo> getUserCollection(int user_id) throws Exception {
        return postTitleInfoDao.getUserCollection(user_id);
    }

    @Override
    public void deleteByPostTitleId(int user_id, int id) throws Exception {
        postTitleInfoDao.deletePostTitleInfoById(user_id, id);
    }

    @Override
    public PostTitleInfo getPostTitleById(int id) throws Exception {
        return postTitleInfoDao.getPostTitleById(id);
    }


}
