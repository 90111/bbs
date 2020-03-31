package com.bbs.service.User;

import com.bbs.model.User.NumInfo;
import com.bbs.model.User.RoleInfo;
import com.bbs.model.User.UserLoginInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserLoginInfoService {

    UserLoginInfo getUserLoginInfoByName(String username) throws Exception;

    void addUserLoginInfo(UserLoginInfo userLoginInfo) throws Exception;

    void deleteUserLoginInfoById(List<UserLoginInfo> ls) throws Exception;

    UserLoginInfo getUserLoginInfoByMail(String mail) throws Exception;

    void updateUserPwd(int id, String newPwd) throws Exception;

    PageInfo getUserLoginInfos(int page) throws Exception;

    void changeUserState(int user_id, String colum_name, int state) throws Exception;

    List<UserLoginInfo> searchUser(String colum_name, String s) throws Exception;

    List<RoleInfo> getRole(int user_id) throws Exception;

    int GetUserNum() throws Exception;

    int selectRegistNowNum() throws Exception;

    List<NumInfo> getAllRegist_time() throws Exception;
}
