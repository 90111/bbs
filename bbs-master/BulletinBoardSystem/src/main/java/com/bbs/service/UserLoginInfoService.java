package com.bbs.service;

import com.bbs.model.User.UserLoginInfo;

public interface UserLoginInfoService {

    public UserLoginInfo getUserLoginInfoByName(String username) throws Exception;


}
