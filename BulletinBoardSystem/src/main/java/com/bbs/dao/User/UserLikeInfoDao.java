package com.bbs.dao.User;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Mapper
public interface UserLikeInfoDao {

    @Select("select count(*) from UserLikeInfo where user_id=#{user_id} and post_title_id=#{post_title_id}")
    int checkIsLike(int user_id, int post_title_id) throws Exception;

    @Insert("insert into UserLikeInfo (user_id, post_title_id, like_time) values (#{user_id}, #{post_title_id}, #{like_time})")
    void addLike(int user_id, int post_title_id, Date like_time) throws Exception;

    @Delete("delete from UserLikeInfo where user_id=#{user_id} and post_title_id=#{post_title_id}")
    void deleteLike(int user_id, int post_title_id) throws Exception;

    //获取当日发帖数据
    @Select("SELECT COUNT(id) FROM UserLikeInfo WHERE DATE(like_time)=CURDATE()")
    int selectLikeNowNum() throws Exception;

}
