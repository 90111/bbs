package com.bbs.service.User;

import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserLoginInfo;

import java.util.List;

public interface UserBaseInfoService {

    UserBaseInfo getUserBaseInfoByUserId(int user_id)throws Exception;

    void updateUserLikeNum(int user_id) throws Exception;

    void updateUserBaseInfo(UserBaseInfo userBaseInfo) throws Exception;

    List<UserBaseInfo> getFollowList(int id) throws Exception;
}
