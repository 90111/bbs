package com.bbs.service.Post;

import com.bbs.model.Post.PostTitleInfo;

import java.util.List;

public interface PostTitleInfoService {

    List<PostTitleInfo> getPostTitleInfos(int id) throws Exception;

    PostTitleInfo getPostTitleContent(int id) throws Exception;

    List<PostTitleInfo> getPostTitleInfosByTime(String s) throws Exception;

    List<PostTitleInfo> getUserRecentPostTitleByUserId(int id) throws Exception;

    void addPostTitleInfo(PostTitleInfo postTitleInfo) throws Exception;

    List<PostTitleInfo> getUserPostTitleByUserId(int id) throws Exception;
}
