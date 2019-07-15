package com.bbs.service.Message;

import com.bbs.model.Message.MessageInfo;

public interface MessageInfoService {

    void addMessage(int send_id, int receive_id) throws Exception;
}
