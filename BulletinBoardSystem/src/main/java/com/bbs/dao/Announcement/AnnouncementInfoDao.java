package com.bbs.dao.Announcement;

import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AnnouncementInfoDao {

    @Select("SELECT * FROM AnnouncementInfo where plate_id is null and district_id is null ORDER BY id DESC limit 1")
    AnnouncementInfo getRecentAnnouncement(int plate_id, int district_id) throws Exception;

    @Insert("insert into AnnouncementInfo (owner, post_time, content, title) values (#{owner}, #{post_time}, #{content}, #{title})")
    void addAnnouncementInfo(AnnouncementInfo announcementInfo) throws Exception;

    @Insert("insert into AnnouncementInfo (plate_id, owner, post_time, content, title) values (#{plate_id}, #{owner}, #{post_time}, #{content}, #{title})")
    void addPlateAnnInfo(AnnouncementInfo announcementInfo) throws Exception;

    @Insert("insert into AnnouncementInfo (district_id, owner, post_time, content, title) values (#{district_id}, #{owner}, #{post_time}, #{content}, #{title})")
    void addDisAnnInfo(AnnouncementInfo announcementInfo) throws Exception;

    @Select("select * from AnnouncementInfo order by id desc")
    List<AnnouncementInfo> getAnnounceInfos() throws Exception;

    @Select("select * from AnnouncementInfo where plate_id = #{plate_id} and district_id is null order by id DESC")
    List<AnnouncementInfo> getAnnounceInfosByPlateId(int plate_id) throws Exception;

    @Select("select * from AnnouncementInfo where plate_id is null and district_id = #{district_id} order by id DESC")
    List<AnnouncementInfo> getAnnounceInfosBydistrictId(int district_id) throws Exception;

    @Select("select * from AnnouncementInfo where plate_id is null and district_id is null")
    List<AnnouncementInfo> getAnnInfos() throws Exception;

    @Select("select * from AnnouncementInfo where ${colum_name}=#{value}")
    List<AnnouncementInfo> getAnnByColumName(String colum_name, int value) throws Exception;

    @Delete("delete from AnnouncementInfo where id in (${s})")
    int batchDelete(@Param("s") String s);

}
