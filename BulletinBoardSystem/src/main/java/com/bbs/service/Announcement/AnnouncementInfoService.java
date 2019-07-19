package com.bbs.service.Announcement;

import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import com.bbs.model.Post.PlateInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AnnouncementInfoService {

    AnnouncementInfo getRecentAnnouncement(int plate_id, int district_id) throws Exception;

    void addAnnouncementInfo(AnnouncementInfo announcementInfo) throws Exception;

    PageInfo<AnnouncementInfo> getAnnounceInfos(int page, int size)throws Exception;

    PageInfo<AnnouncementInfo> getAnnounceInfos(int plate_id, int page, int size) throws Exception;

    PageInfo<AnnouncementInfo> getAnnounceInfos2(int district_id, int page, int size) throws Exception;

    void batchDelete(List<AnnouncementInfo> ls) throws Exception;

    PageInfo<AnnouncementInfo> getAnnInfos(int page, int size) throws Exception;

    PageInfo<AnnouncementInfo> getAnnByColumName(String colum_name, int value, int page, int size) throws Exception;
}
