package com.bbs.dao.Announcement;

import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AnnouncementInfoDao {

    @Select("SELECT * FROM AnnouncementInfo where plate_id=#{plate_id} and district_id=#{district_id} ORDER BY id DESC limit 1")
    AnnouncementInfo getRecentAnnouncement(int plate_id, int district_id) throws Exception;

    @Delete("DELETE FROM AnnouncementInfo WHERE id = #{id}")
    void deleteAnnouncementInfoById (int id) throws Exception;

    @Insert("insert into AnnouncementInfo (owner, post_time, content) values (#{owner}, #{post_time}, #{content})")
    void addAnnouncementInfo(AnnouncementInfo announcementInfo) throws Exception;

    @Select("select * from AnnouncementInfo order by id")
    List<AnnouncementInfo> getAnnounceInfos() throws Exception;

    @Select("select * from AnnouncementInfo where plate_id = #{plate_id}")
    List<AnnouncementInfo> getAnnounceInfosByPlateId(int plate_id) throws Exception;

    @Select("select * from AnnouncementInfo where district_id = #{district_id}")
    List<AnnouncementInfo> getAnnounceInfosByDistrictId(int district_id) throws Exception;

}
