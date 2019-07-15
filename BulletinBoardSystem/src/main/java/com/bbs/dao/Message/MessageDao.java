package com.bbs.dao.Message;


import com.bbs.model.Message.MessageInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MessageDao {

    @Insert("insert into MessageInfo (send_user_id,receive_user_id,send_time,content) values (#{send_user_id},#{receive_user_id},#{send_time},#{content})")
    void addMessage(MessageInfo messageInfo) throws Exception;
}
