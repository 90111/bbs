package com.bbs.service.Message.Impl;

import com.bbs.dao.Message.MessageDao;
import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.model.Message.MessageInfo;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.service.Message.MessageInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MessageInfoInfoServiceImpl implements MessageInfoService {

    @Resource
    private MessageDao messageDao;

    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Override
    public void addMessage(int send_id, int receive_id) throws Exception {
        UserBaseInfo info = userBaseInfoDao.getUserBaseInfoByUserId(send_id);
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setSend_user_id(send_id);
        messageInfo.setReceive_user_id(receive_id);
        messageInfo.setContent(info.getNick_name()+"关注了你");
        messageInfo.setSend_time(new Date());
        messageDao.addMessage(messageInfo);
    }
}
