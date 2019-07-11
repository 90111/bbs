package com.bbs.dao.Post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserLikeInfoDao {

    @Select("select count(*) from UserLikeInfo where user_id=#{user_id} and post_title_id=#{post_title_id}")
    int checkIsLike(int user_id, int post_title_id) throws Exception;
}
