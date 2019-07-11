package com.bbs.service.Post;

import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.UserCollectionInfo;

import java.util.List;
import java.util.Map;

public interface PostTitleInfoService {

    List<PostTitleInfo> getPostTitleInfos(int id) throws Exception;

    PostTitleInfo getPostTitleContent(int id) throws Exception;

    List<PostTitleInfo> getPostTitleInfosByTime(String s) throws Exception;

    List<PostTitleInfo> getUserRecentPostTitleByUserId(int id) throws Exception;

    void addPostTitleInfo(PostTitleInfo postTitleInfo) throws Exception;

    List<PostTitleInfo> getUserPostTitleByUserId(int id) throws Exception;

    List<UserCollectionInfo> getUserCollection(int user_id) throws Exception;

    void deleteByPostTitleId(int user_id, int id) throws Exception;

    PostTitleInfo getPostTitleById(int id) throws Exception;
}