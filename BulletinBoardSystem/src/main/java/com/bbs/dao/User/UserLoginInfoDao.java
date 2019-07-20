package com.bbs.dao.User;

import com.bbs.model.User.FunctionInfo;
import com.bbs.model.User.RoleInfo;
import com.bbs.model.User.UserLoginInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserLoginInfoDao {

    @Select("select * from UserLoginInfo where user_name = #{user_name}")
    UserLoginInfo getUserLoginInfoByName(String user_name) throws Exception;

    @Select("select * from UserLoginInfo where id = #{id}")
    UserLoginInfo getUserLoginInfoById(int id) throws Exception;

    @Select("SELECT * from RoleInfo where id in (select role_info_id from RoleUserInfo where user_info_id = #{id})")
    List<RoleInfo> LoadRolePermission(int id) throws Exception;

    @Select("select * from FunctionInfo where id in (select function_info_id from RoleFunctionPermission where role_info_id = #{role_info_id})")
    List<FunctionInfo> getFunctions (int role_info_id) throws Exception;

    @Insert("insert into UserLoginInfo (user_name, `password`, mail) VALUES (#{user_name}, #{password}, #{mail})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void addUserLoginInfo(UserLoginInfo userLoginInfo) throws Exception;

    @Select("select * from UserLoginInfo where mail = #{mail}")
    UserLoginInfo getUserLoginInfoByMail(String mail)throws Exception;

    @Update("update UserLoginInfo set password=#{password} where id=#{id}")
    void updateUserPwd(int id, String password) throws Exception;

    @Select("select * from UserLoginInfo order by id")
    List<UserLoginInfo> getUserLoginInfos() throws Exception;

    @Delete("delete from UserLoginInfo where id in (${s})")
    void deleteUserLoginInfoById(@Param("s") String s) throws Exception;

    @Update("update UserLoginInfo set ${colum_name}=#{state} where id=#{id}")
    void changeUserState(String colum_name, int id, int state) throws Exception;

    @Select("select * from UserLoginInfo, UserBaseInfo where ${colum_name} like #{s} and UserLoginInfo.id=UserBaseInfo.user_id")
    List<UserLoginInfo> getUserLoginInfoByColum(String colum_name,  String s) throws Exception;

    //获取当日发帖数据
    @Select("SELECT COUNT(id) FROM UserLoginInfo WHERE DATE(regist_time)=CURDATE()")
    int selectRegistNowNum() throws Exception;
}
