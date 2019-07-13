package com.bbs.dao.User;

import com.bbs.model.User.UserBaseInfo;
import com.bbs.model.User.UserFollowInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserFollowDao {

    @Insert("insert into UserFollowInfo (user_id, follow_id) VALUES (#{user_id}, #{follow_id})")
    void addFollowed(int user_id, int follow_id) throws Exception;

    @Delete("delete from UserFollowInfo where user_id=#{user_id} and follow_id=#{follow_id}")
    void deleteFollowed(int user_id, int follow_id) throws Exception;

    @Select("select * from UserFollowInfo where user_id=#{user_id} and follow_id=#{follow_id}")
    UserFollowInfo chekIsFollowed(int user_id, int follow_id) throws Exception;

}
