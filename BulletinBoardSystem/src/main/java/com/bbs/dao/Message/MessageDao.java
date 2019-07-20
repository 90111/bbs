package com.bbs.dao.Message;


import com.bbs.model.Message.MessageInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MessageDao {

    @Insert("insert into MessageInfo (send_user_id,receive_user_id,send_time,content,type) values " +
            "(#{send_user_id},#{receive_user_id},#{send_time},#{content},#{type})")
    void addMessage(MessageInfo messageInfo) throws Exception;

    @Select("select * from MessageInfo where receive_user_id=#{receive_user_id} and " +
            "type=#{type} order by state, send_time desc")
    List<MessageInfo> getMessageInfos(int receive_user_id, int type) throws Exception;

    @Select("select count(*) from MessageInfo where receive_user_id=#{receive_user_id} and state=7")
    int getMessageNum(int receive_user_id) throws Exception;

    @Select("select count(*) from MessageInfo where receive_user_id=#{receive_user_id} and state=7 and type=#{type}")
    int getMessageNumByType(int receive_user_id, int type) throws Exception;

    @Update("update MessageInfo set state=8 where receive_user_id=#{receive_user_id} and type=#{type}")
    void readMessageByType(int receive_user_id, int type) throws Exception;
}
