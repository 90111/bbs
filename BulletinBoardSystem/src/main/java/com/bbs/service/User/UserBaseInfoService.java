package com.bbs.service.User;

import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserLoginInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserBaseInfoService {

    UserBaseInfo getUserBaseInfoByUserId(int user_id)throws Exception;

    void updateUserLikeNum(int user_id) throws Exception;

    void updateUserBaseInfo(UserBaseInfo userBaseInfo) throws Exception;

    List<UserBaseInfo> getFollowList(int id) throws Exception;

    List<UserBaseInfo> getFansList(int id) throws Exception;

    PageInfo getUserBaseInfos(int page) throws Exception;
}
