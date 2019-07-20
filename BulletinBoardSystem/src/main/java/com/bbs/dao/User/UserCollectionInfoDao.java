package com.bbs.dao.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;

@Mapper
@Component
public interface UserCollectionInfoDao {

    @Select("select count(*) from CollectionInfo where user_id=#{user_id} and post_title_id=#{post_title_id}")
    int checkIsCollected(int user_id, int post_title_id) throws Exception;

    @Insert("insert into CollectionInfo (user_id, post_title_id, collect_time) values (#{user_id}, #{post_title_id}, #{collect_time})")
    void addCollection(int user_id, int post_title_id, Date collect_time) throws Exception;

    @Delete("delete from CollectionInfo where user_id=#{user_id} and post_title_id=#{post_title_id}")
    void deleteCollection(int user_id, int post_title_id) throws Exception;

    //获取当日发帖数据
    @Select("SELECT COUNT(id) FROM CollectionInfo WHERE DATE(collect_time)=CURDATE()")
    int selectCollectNowNum() throws Exception;
}
