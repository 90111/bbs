package com.bbs.service.User;

public interface UserLikeInfoService {

    boolean checkIsLike(int user_id, int post_title_id) throws Exception;

    boolean changeLike(int user_id, int post_title_id) throws Exception;

    int selectLikeNowNum() throws Exception;

}
