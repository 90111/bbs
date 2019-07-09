package com.bbs.service.User;

import com.bbs.model.User.UserLoginInfo;

public interface UserLoginInfoService {

    UserLoginInfo getUserLoginInfoByName(String username) throws Exception;

    void addUserLoginInfo(UserLoginInfo userLoginInfo) throws Exception;

}
