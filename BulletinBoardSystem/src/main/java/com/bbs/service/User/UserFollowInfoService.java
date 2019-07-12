package com.bbs.service.User;

import com.bbs.model.User.UserFollowInfo;

public interface UserFollowInfoService {

    boolean chekIsFollowed(int user_id, int follow_id) throws Exception;

    void changeFollowed(int user_id, int follow_id) throws Exception;
}
