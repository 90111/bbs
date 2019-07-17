package com.bbs.api.Admin;

import com.bbs.model.User.RoleInfo;
import com.bbs.model.User.UserLoginInfo;
import com.bbs.service.User.Impl.UserLoginInfoServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class Login {

    @Autowired
    private UserLoginInfoServiceImpl userLoginInfoService;

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public Map adminLogin(@RequestBody UserLoginInfo userLoginInfo) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginInfo.getUser_name(),
                userLoginInfo.getPassword());
        currentUser.login(usernamePasswordToken);
        UserLoginInfo info = userLoginInfoService.getUserLoginInfoByName((String)currentUser.getPrincipal());
        info.setRoleInfos(userLoginInfoService.getRole(info.getId()));
        if(currentUser.hasRole("admin")){
            map.put("code", "200");
            map.put("data", info);
            map.put("msg", "登录成功");
        }else {
            map.put("code", "500");
            map.put("msg", "用户无权限");
        }
        return map;
    }
}
