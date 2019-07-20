package com.bbs.service.Message;

import com.bbs.model.Message.MessageInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MessageInfoService {

    void addMessage(MessageInfo messageInfo) throws Exception;

    PageInfo<MessageInfo> getMessageInfos(int receive_user_id, int type, int page, int size) throws Exception;

    int getMessageNum(int receive_user_id) throws Exception;

    void readMessageByType(int receive_user_id, int type) throws Exception;

    int getMessageNumByType(int receive_user_id, int type) throws Exception;
}
