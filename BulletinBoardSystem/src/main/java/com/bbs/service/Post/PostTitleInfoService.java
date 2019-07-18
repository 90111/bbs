package com.bbs.service.Post;

import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.model.User.UserCollectionInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PostTitleInfoService {

    PageInfo<PostTitleInfo> getPostTitleInfos(int id, String s, int page, int size) throws Exception;

    PostTitleInfo getPostTitleContent(int id) throws Exception;

    PageInfo<PostTitleInfo> getPostTitleInfosByTime(String s, int page, int size) throws Exception;

    List<PostTitleInfo> getUserRecentPostTitleByUserId(int id) throws Exception;

    void addPostTitleInfo(PostTitleInfo postTitleInfo) throws Exception;

    List<PostTitleInfo> getUserPostTitleByUserId(int id) throws Exception;

    List<UserCollectionInfo> getUserCollection(int user_id) throws Exception;

    void deleteByPostTitleId(int user_id, int id) throws Exception;

    PostTitleInfo getPostTitleById(int id) throws Exception;

    List<PostTitleInfo> getPostTitleBetweenTime(String date1, String date2) throws Exception;

    List<PostTitleInfo> getRecommendPostTitles(int district_id) throws Exception;

    void updatePostTitleInfo(PostTitleInfo postTitleInfo) throws Exception;

    List<PostTitleInfo> searchPost(String postTitle) throws Exception;

    PageInfo getPostTitleInfosByColum(String colum_name, String s, String nick_name, int page, int size) throws Exception;

    void batchDelete(List<PostTitleInfo> ls) throws Exception;

    void changePostState(int id, String colum_name, int state) throws Exception;

    void changePostDis(int id, String colum_name, int state) throws Exception;

    PageInfo<PostTitleInfo> getPostTitleInfos(int id, String s, int page, int size, int flag) throws Exception;

    int selectPostNowNum() throws Exception;

}
