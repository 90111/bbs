package com.bbs.service.Announcement.lmpl;

import com.bbs.dao.Announcement.AnnouncementInfoDao;
import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import com.bbs.service.Announcement.AnnouncementInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AnnouncementInfoServiceImpl implements AnnouncementInfoService {

    @Resource
    private AnnouncementInfoDao announcementInfoDao;

    @Override
    public AnnouncementInfo getTopAnnouncementInfo() throws Exception{
        return announcementInfoDao.getTopAnnouncementInfo();
    }


    @Override
    public void deleteAnnouncementInfoById (int id) throws Exception{
        announcementInfoDao.deleteAnnouncementInfoById(id);
    }

    @Override
    public void addAnnouncementInfo(AnnouncementInfo announcementInfo) throws Exception{
        announcementInfoDao.addAnnouncementInfo(announcementInfo);
    }


}
