package com.bbs.service.User;

public interface UserLikeInfoService {

    boolean checkIsLike(int user_id, int post_title_id) throws Exception;

    void changeLike(int user_id, int post_title_id) throws Exception;

}
