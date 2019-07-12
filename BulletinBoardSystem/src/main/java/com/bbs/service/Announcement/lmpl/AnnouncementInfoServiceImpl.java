package com.bbs.service.Announcement.lmpl;

import com.bbs.dao.Announcement.AnnouncementInfoDao;
import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import com.bbs.service.Announcement.AnnouncementInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class AnnouncementInfoServiceImpl implements AnnouncementInfoService {

    @Resource
    private AnnouncementInfoDao announcementInfoDao;

    @Override
    public AnnouncementInfo getRecentAnnouncement(int plate_id, int district_id) throws Exception{
        return announcementInfoDao.getRecentAnnouncement(plate_id, district_id);
    }


    @Override
    public void deleteAnnouncementInfoById (int id) throws Exception{
        announcementInfoDao.deleteAnnouncementInfoById(id);
    }

    @Override
    public void addAnnouncementInfo(AnnouncementInfo announcementInfo) throws Exception{
        announcementInfo.setPost_time(new Date());
        announcementInfoDao.addAnnouncementInfo(announcementInfo);
    }


}
