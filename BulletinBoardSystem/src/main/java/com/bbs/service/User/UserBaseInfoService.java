package com.bbs.service.User;

import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserLoginInfo;

public interface UserBaseInfoService {

    public UserBaseInfo getUserBaseInfoByUserId(int user_id)throws Exception;
}
