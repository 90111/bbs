package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.DistrictInfoDao;
import com.bbs.dao.Post.PlateInfoDao;
import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.dao.User.UserCollectionInfoDao;
import com.bbs.dao.User.UserLikeInfoDao;
import com.bbs.dao.User.UserLoginInfoDao;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.UserCollectionInfo;
import com.bbs.service.Post.PostTitleInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

    @Resource
    private UserLoginInfoDao userLoginInfoDao;

    @Resource
    private UserLikeInfoDao userLikeInfoDao;

    @Resource
    private UserCollectionInfoDao userCollectionInfoDao;

    @Override
    public PageInfo<PostTitleInfo> getPostTitleInfos(int id, String s, int page) throws Exception {
        PageHelper.startPage(page, 20);
        Subject currentUser = SecurityUtils.getSubject();
        List<PostTitleInfo> ls = postTitleInfoDao.getPostTitleInfos(id, s);
        if (currentUser.isAuthenticated()) {
            String username = (String) currentUser.getPrincipal();
            int user_id = userLoginInfoDao.getUserLoginInfoByName(username).getId();
            for (PostTitleInfo info : ls){
                if ((userCollectionInfoDao.checkIsCollected(user_id, info.getId()) == 1)){
                    info.setCollected(true);
                }
                if ((userLikeInfoDao.checkIsLike(user_id, info.getId())) == 1){
                    info.setLiked(true);
                }
            }
        }
        PageInfo<PostTitleInfo> pageInfoDemo = new PageInfo<PostTitleInfo>(ls);
        return pageInfoDemo;
    }

    @Override
    public PostTitleInfo getPostTitleContent(int id) throws Exception {
        postTitleInfoDao.updatePostTitleViewNum(id);
        return postTitleInfoDao.getPostTitleContent(id);
    }

    @Override
    public List<PostTitleInfo> getPostTitleInfosByTime(String s) throws Exception {
//        PageHelper.startPage(1, 5);
        List<PostTitleInfo> ls = postTitleInfoDao.getPostTitleInfosByTime(s);
//        PageInfo<PostTitleInfo> pageInfo = new PageInfo<PostTitleInfo>(ls);
//        System.out.println(pageInfo.getTotal());
        return ls;
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

    @Override
    public PageInfo<PostTitleInfo> getInfos() throws Exception {
        PageHelper.startPage(2, 5);
        List<PostTitleInfo> learnResourceList = postTitleInfoDao.getInfos();
        PageInfo<PostTitleInfo> pageInfoDemo = new PageInfo<PostTitleInfo>(learnResourceList);
        return pageInfoDemo;
    }


}
