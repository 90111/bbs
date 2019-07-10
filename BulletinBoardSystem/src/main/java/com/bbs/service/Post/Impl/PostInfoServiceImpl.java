package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.service.Post.PostInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class PostInfoServiceImpl implements PostInfoService {

    @Resource
    private PostTitleInfoDao postTitleInfoDao;

    @Override
    public List<PostTitleInfo> getPostTitleInfos(int id) throws Exception {
        List<PostTitleInfo> ls = postTitleInfoDao.getPostTitleInfos(id);

        return ls;
    }

    @Override
    public PostTitleInfo getPostTitleContent(int id) throws Exception {
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


}
