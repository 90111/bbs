package com.bbs.service.Post.Impl;

public interface UserLikeInfoService {

    boolean checkIsLike(int user_id, int post_title_id) throws Exception;

    void changeLike(int user_id, int post_title_id) throws Exception;
}
