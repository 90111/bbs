package com.bbs.service.User;

import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserLoginInfo;

public interface UserBaseInfoService {

    UserBaseInfo getUserBaseInfoByUserId(int user_id)throws Exception;

    void updateUserLikeNum(int user_id) throws Exception;

    void updateUserBaseInfo(UserBaseInfo userBaseInfo) throws Exception;
}
