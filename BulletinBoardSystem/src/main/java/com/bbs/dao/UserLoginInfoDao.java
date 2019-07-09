package com.bbs.dao;

import com.bbs.model.User.FunctionInfo;
import com.bbs.model.User.RoleInfo;
import com.bbs.model.User.UserLoginInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserLoginInfoDao {

    @Select("select * from UserLoginInfo where user_name = #{user_name}")
    public UserLoginInfo getUserLoginInfoByName(String user_name) throws Exception;

    @Select("SELECT * from RoleInfo where id in (select role_info_id from RoleUserInfo where user_info_id = #{id})")
    public List<RoleInfo> LoadRolePermission(int id) throws Exception;

    @Select("select * from RoleInfo")
    public List<RoleInfo> getRoles() throws Exception;

    @Select("select * from FunctionInfo where id in (select function_info_id from RoleFunctionPermission where role_info_id = #{role_info_id})")
    public List<FunctionInfo> getFunctions (int role_info_id) throws Exception;

    @Insert("insert into UserLoginInfo (user_name, `password`, mail) VALUES (#{user_name}, #{password}, #{mail})")
    public void AddUserLoginInfo(UserLoginInfo userLoginInfo) throws Exception;

}
