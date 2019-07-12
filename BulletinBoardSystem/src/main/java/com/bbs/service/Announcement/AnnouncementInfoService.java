package com.bbs.service.Announcement;

import com.bbs.model.AnnouncementInfo.AnnouncementInfo;

import java.util.List;

public interface AnnouncementInfoService {

    AnnouncementInfo getRecentAnnouncement(int plate_id, int district_id) throws Exception;

    void deleteAnnouncementInfoById (int id) throws Exception;

    void addAnnouncementInfo(AnnouncementInfo announcementInfo) throws Exception;
}
