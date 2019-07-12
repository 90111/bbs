package com.bbs.config;

import com.bbs.model.User.FunctionInfo;
import com.bbs.model.User.RoleInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.User.UserLoginInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class Realm extends AuthorizingRealm {
    @Autowired
    private UserLoginInfoService userLoginInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("调用权限加载函数");
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserLoginInfo userLoginInfo = null;
        try {
            userLoginInfo = userLoginInfoService.getUserLoginInfoByName(username);
            for (RoleInfo role : userLoginInfo.getRoleInfos()){
                simpleAuthorizationInfo.addRole(role.getCode());
                for (FunctionInfo functionInfo : role.getFunctionInfos()){
                    System.out.println(functionInfo.getCode());
                    simpleAuthorizationInfo.addStringPermission(functionInfo.getCode());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

            System.out.println("权限加载函数调用完毕");

        return simpleAuthorizationInfo;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("调用用户验证函数");
        String username = (String) authenticationToken.getPrincipal();
        UserLoginInfo userLoginInfo = null;
        try {
            userLoginInfo = userLoginInfoService.getUserLoginInfoByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userLoginInfo == null){
                return null;
            }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userLoginInfo.getUser_name(), userLoginInfo.getPassword(), getName());

        return simpleAuthenticationInfo;
    }
}
