package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.DistrictInfoDao;
import com.bbs.dao.Post.PlateInfoDao;
import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.dao.User.UserBaseInfoDao;
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

    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Resource
    private DistrictInfoDao districtInfoDao;

    @Resource
    private PlateInfoDao plateInfoDao;

    @Override
    public List<PostTitleInfo> getPostTitleInfos(int id, String s) throws Exception {
        List<PostTitleInfo> ls = postTitleInfoDao.getPostTitleInfos(id, s);

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
        userBaseInfoDao.updateUserPostNum(postTitleInfo.getOwner());
        districtInfoDao.updateDistrictPostNum(postTitleInfo.getDistrictInfo_id());
        plateInfoDao.updatePlatePostNum(districtInfoDao.getDistrictInfo(postTitleInfo.getDistrictInfo_id()).getPlate_id());
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
        PostTitleInfo postTitleInfo = postTitleInfoDao.getPostTitleById(user_id);
        postTitleInfoDao.deletePostTitleInfoById(user_id, id);
        userBaseInfoDao.updateUserPostNum(user_id);
        districtInfoDao.updateDistrictPostNum(postTitleInfo.getDistrictInfo_id());
        plateInfoDao.updatePlatePostNum(districtInfoDao.getDistrictInfo(postTitleInfo.getDistrictInfo_id()).getPlate_id());
    }

    @Override
    public PostTitleInfo getPostTitleById(int id) throws Exception {
        return postTitleInfoDao.getPostTitleById(id);
    }

    @Override
    public List<PostTitleInfo> getPostTitleBetweenTime(String date1, String date2) throws Exception {
        return postTitleInfoDao.getPostTitleBetweenTime(date1, date2);
    }

    @Override
    public List<PostTitleInfo> getRecommendPostTitles(int district_id) throws Exception {
        return postTitleInfoDao.getRecommendPostTitles(district_id);
    }

    @Override
    public void updatePostTitleInfo(PostTitleInfo postTitleInfo) throws Exception {
        postTitleInfo.setPost_time(new Date());
        postTitleInfoDao.updatePostTitleInfo(postTitleInfo);
    }

    @Override
    public List<PostTitleInfo> searchPost(String postTitle) throws Exception {
        try {
            return postTitleInfoDao.searchPost("%"+postTitle+"%");
        }catch (Exception e){
            return null;
        }
    }


}
