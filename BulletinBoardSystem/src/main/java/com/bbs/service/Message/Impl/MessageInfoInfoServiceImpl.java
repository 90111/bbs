package com.bbs.service.Message.Impl;

import com.bbs.dao.Message.MessageDao;
import com.bbs.dao.Post.PostTitleInfoDao;
import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.model.Message.MessageInfo;
import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.UserBaseInfo;
import com.bbs.service.Message.MessageInfoService;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MessageInfoInfoServiceImpl implements MessageInfoService {

    @Resource
    private MessageDao messageDao;

    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Resource
    private PostTitleInfoDao postTitleInfoDao;

    @Override
    public void addMessage(MessageInfo messageInfo) throws Exception {
        messageInfo.setSend_time(new Date());
        messageDao.addMessage(messageInfo);
    }

    @Override
    public PageInfo<MessageInfo> getMessageInfos(int receive_user_id, int type, int page, int size) throws Exception {
        List<MessageInfo> ls = messageDao.getMessageInfos(receive_user_id, type);
        PageHelper.startPage(page, size);
        for (MessageInfo info : ls){
            info.setUserBaseInfo(userBaseInfoDao.getUserBaseInfoByUserId(info.getSend_user_id()));
            String[] s = info.getContent().split("\\&");
            if (s.length > 1){
                info.setContent(s[0]);
                PostTitleInfo postTitleInfo = new PostTitleInfo();
                postTitleInfo = postTitleInfoDao.getPostTitleById(Integer.parseInt(s[s.length-1]));
                postTitleInfo.setContent("");
                info.setPostTitleInfo(postTitleInfo);
            }
        }
        return new PageInfo<MessageInfo>(ls);
    }

    @Override
    public int getMessageNum(int receive_user_id) throws Exception {
        return messageDao.getMessageNum(receive_user_id);
    }

    @Override
    public void readMessageByType(int receive_user_id, int type) throws Exception {
        messageDao.readMessageByType(receive_user_id, type);
    }

    @Override
    public int getMessageNumByType(int receive_user_id, int type) throws Exception {
        return messageDao.getMessageNumByType(receive_user_id, type);
    }
}
