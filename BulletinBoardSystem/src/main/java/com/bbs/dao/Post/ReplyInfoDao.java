package com.bbs.dao.Post;

import com.bbs.model.User.FunctionInfo;
import com.bbs.model.User.RoleInfo;
import com.bbs.model.Post.ReplyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ReplyInfoDao {

    @Insert("insert into ReplyInfo (post_title_id,user_id,content,reply_time) VALUES (#{post_title_id},#{user_id},#{content},#{reply_time})")
    void addReplyInfo(ReplyInfo replyInfo) throws Exception;

    @Select("select ReplyInfo.id, ReplyInfo.user_id, ReplyInfo.content, UserBaseInfo.nick_name, UserBaseInfo.icon from ReplyInfo, UserBaseInfo where post_title_id=#{post_title_id} and ReplyInfo.user_id = UserBaseInfo.user_id")
    List<ReplyInfo> getReplyInfos(int post_title_id) throws Exception;

}
