package com.bbs.service.User.Impl;


import com.bbs.dao.User.RoleInfoDao;
import com.bbs.dao.User.RoleUserInfoDao;
import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.dao.User.UserLoginInfoDao;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.NumInfo;
import com.bbs.model.User.RoleInfo;
import com.bbs.model.User.RoleUserInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.User.UserLoginInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class UserLoginInfoServiceImpl implements UserLoginInfoService {

    @Resource
    private UserLoginInfoDao userLoginInfoDao;
    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Resource
    private RoleInfoDao roleInfoDao;
    @Resource
    private RoleUserInfoDao roleUserInfoDao;


    public UserLoginInfo getUserLoginInfoByName(String username) throws Exception {
        UserLoginInfo userLoginInfo = userLoginInfoDao.getUserLoginInfoByName(username);
        if (userLoginInfo == null) {
            return null;
        }
        userLoginInfo.setRoleInfos(userLoginInfoDao.LoadRolePermission(userLoginInfo.getId()));
        for (RoleInfo roleInfo : userLoginInfo.getRoleInfos()) {
            roleInfo.setFunctionInfos(userLoginInfoDao.getFunctions(roleInfo.getId()));
        }
        return userLoginInfo;
    }

    @Override
    public void addUserLoginInfo(UserLoginInfo userLoginInfo) throws Exception {
        userLoginInfoDao.addUserLoginInfo(userLoginInfo);
        userBaseInfoDao.addUserBaseInfo(userLoginInfo.getId(), userLoginInfo.getUser_name());
        roleUserInfoDao.addRoleUserInfo(userLoginInfo.getId());
    }

    @Override
    public void deleteUserLoginInfoById(List<UserLoginInfo> ls) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (UserLoginInfo info : ls) {
            sb.append("'").append(info.getId()).append("'").append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        String s = sb.toString();
        userLoginInfoDao.deleteUserLoginInfoById(s);
    }

    @Override
    public UserLoginInfo getUserLoginInfoByMail(String mail) throws Exception {
        UserLoginInfo userLoginInfo = userLoginInfoDao.getUserLoginInfoByMail(mail);
        if (userLoginInfo == null) {
            return null;
        }
        return userLoginInfo;
    }

    @Override
    public void updateUserPwd(int id, String newPwd) throws Exception {
        userLoginInfoDao.updateUserPwd(id, newPwd);
    }

    @Override
    public PageInfo<UserLoginInfo> getUserLoginInfos(int page) throws Exception {
        PageHelper.startPage(page, 20);
        List<UserLoginInfo> ls = userLoginInfoDao.getUserLoginInfos();
        for (UserLoginInfo info : ls){
            List<RoleInfo> roleInfoList = roleInfoDao.getRoles(roleUserInfoDao.getRoleUserInfo(info.getId()).getRole_info_id());
            info.setRoleInfos(roleInfoList);
        }
        PageInfo<UserLoginInfo> pageInfoDemo = new PageInfo<UserLoginInfo>(ls);
        return pageInfoDemo;
    }

    @Override
    public void changeUserState(int user_id, String colum_name, int state) throws Exception {
        userLoginInfoDao.changeUserState(colum_name, user_id, state);
    }

    @Override
    public List<UserLoginInfo> searchUser(String colum_name, String s) throws Exception {
        List<UserLoginInfo> ls = new LinkedList<>();
        ls = userLoginInfoDao.getUserLoginInfoByColum(colum_name, "%" + s + "%");
        for (UserLoginInfo info : ls){
            List<RoleInfo> roleInfoList = roleInfoDao.getRoles(roleUserInfoDao.getRoleUserInfo(info.getId()).getRole_info_id());
            info.setRoleInfos(roleInfoList);
            info.setUserBaseInfo(userBaseInfoDao.getUserBaseInfoByUserId(info.getId()));
        }
        return ls;
    }

    @Override
    public List<RoleInfo> getRole(int user_id) throws Exception {
        return roleInfoDao.getRoles(roleUserInfoDao.getRoleUserInfo(user_id).getRole_info_id());
    }

    @Override
    public int GetUserNum() throws Exception {
        return userLoginInfoDao.GetUserNum();
    }

    @Override
    public int selectRegistNowNum() throws Exception {
        return userLoginInfoDao.selectRegistNowNum();
    }

    @Override
    public List<NumInfo> getAllRegist_time() throws Exception {
        return userLoginInfoDao.getAllRegist_time();
    }
}
