package com.bbs.service.User;

public interface UserCollectionInfoService {

    boolean checkIsCollected(int user_id, int post_title_id) throws Exception;

    boolean changeCollection(int user_id, int post_title_id) throws Exception;

    int selectCollectNowNum() throws Exception;
}
