package com.bbs.service.Post;

import com.bbs.model.Post.PostTitleInfo;

import javax.annotation.Resource;
import java.util.List;

public interface PostInfoService {

    List<PostTitleInfo> getPostTitleInfos(int id) throws Exception;

    PostTitleInfo getPostTitleContent(int id) throws Exception;

    List<PostTitleInfo> getPostTitleInfosByTime(String s) throws Exception;
}
