package com.bbs.dao.User;

import com.bbs.model.User.RoleUserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RoleUserInfoDao {

    @Select("select * from RoleUserInfo where user_info_id=#{id} ")
    RoleUserInfo getRoleUserInfo(int id) throws Exception;

    @Update("update RoleUserInfo set role_info_id=#{role} where user_info_id=#{user_info_id}")
    void changeUserRole(int role, int user_info_id) throws Exception;

    @Insert("insert into RoleUserInfo values (#{user_info_id},1)")
    void addRoleUserInfo(int user_info_id) throws Exception;
}
