package com.bbs.dao.User;

import com.bbs.model.User.UserBaseInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserBaseInfoDao {

    @Select("select * from UserBaseInfo where user_id = #{user_id}")
    UserBaseInfo getUserBaseInfoByUserId(int user_id) throws Exception;

    @Insert("insert into UserBaseInfo (user_id, icon) values (#{user_id}, " + "'/icon.png'" + ")")
    void addUserBaseInfo(int user_id) throws Exception;

    @Delete("delete from UserBaseInfo where user_id = #{user_id}")
    void deleteUserBaseInfoById(int user_id) throws Exception;
}
