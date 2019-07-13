package com.bbs.dao.User;

import com.bbs.model.User.UserBaseInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserBaseInfoDao {

    @Select("select * from UserBaseInfo where user_id = #{user_id}")
    UserBaseInfo getUserBaseInfoByUserId(int user_id) throws Exception;

    @Insert("insert into UserBaseInfo (user_id, nick_name, icon) values (#{user_id}, #{nick_name}, " + "'/icon.png'" + ")")
    void addUserBaseInfo(int user_id, String nick_name) throws Exception;

    @Delete("delete from UserBaseInfo where user_id = #{user_id}")
    void deleteUserBaseInfoById(int user_id) throws Exception;

    @Update("update UserBaseInfo set follow_num = (select count(*) from UserFollowInfo where user_id = #{user_id}) WHERE user_id = #{user_id}")
    void updateUserFollowNum(int user_id) throws Exception;

    @Update("update UserBaseInfo set fans_num = (select count(*) from UserFollowInfo where follow_id = #{user_id}) WHERE user_id = #{user_id}")
    void updateUserFansNum(int user_id) throws Exception;

    @Update("update UserBaseInfo set UserBaseInfo.like_num = (select sum(PostTitleInfo.like_num) from PostTitleInfo where `owner` = #{user_id}) where user_id = #{user_id}")
    void updateUserLikeNum(int user_id) throws Exception;

    @Update("update UserBaseInfo set post_num = (select count(*) from PostTitleInfo where owner=#{user_id}) where user_id=#{user_id}")
    void updateUserPostNum(int user_id) throws Exception;

    @Update("update UserBaseInfo set nick_name=#{nick_name},sex=#{sex},birth=#{birth},icon=#{icon},motto=#{motto} where user_id=#{user_id}")
    void updateUserBaseInfo(UserBaseInfo userBaseInfo) throws Exception;
}
