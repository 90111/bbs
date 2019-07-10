package com.bbs.dao.Announcement;

import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AnnouncementInfoDao {

    @Select("SELECT id,owner,content,post_time FROM AnnouncementInfo ORDER BY id DESC limit 1")
    AnnouncementInfo getTopAnnouncementInfo() throws Exception;

    @Delete("DELETE FROM AnnouncementInfo WHERE id = #{id}")
    void deleteAnnouncementInfoById (int id) throws Exception;

    @Insert("insert into AnnouncementInfo (owner, post_time, content) values (#{owner}, #{post_time}, #{content})")
    void addAnnouncementInfo(AnnouncementInfo announcementInfo) throws Exception;

}
