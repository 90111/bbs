package com.bbs.service.Post;

import com.bbs.model.Post.ReplyInfo;

import java.util.List;

public interface ReplyInfoService {

    void addReplyInfoService(ReplyInfo replyInfo) throws Exception;

    List<ReplyInfo> getReplyInfos(int post_title_id) throws Exception;

    int selectReplyNowNum() throws Exception;
}
