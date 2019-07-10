package com.bbs.service.Post.Impl;

import com.bbs.dao.Post.ReplyInfoDao;
import com.bbs.model.Post.ReplyInfo;
import com.bbs.service.Post.ReplyInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ReplyInfoServiceImpl implements ReplyInfoService {

    @Resource
    private ReplyInfoDao replyInfoDao;

    @Override
    public void addReplyInfoService(ReplyInfo replyInfo) throws Exception {
        replyInfo.setReply_time(new Date());
        replyInfoDao.addReplyInfo(replyInfo);
    }

    @Override
    public List<ReplyInfo> getReplyInfos(int post_title_id) throws Exception {
        return replyInfoDao.getReplyInfos(post_title_id);
    }
}
