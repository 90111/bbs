package com.bbs.service.Announcement.lmpl;

import com.bbs.dao.Announcement.AnnouncementInfoDao;
import com.bbs.dao.Post.DistrictInfoDao;
import com.bbs.dao.Post.PlateInfoDao;
import com.bbs.dao.User.UserBaseInfoDao;
import com.bbs.model.AnnouncementInfo.AnnouncementInfo;
import com.bbs.model.Post.PlateInfo;
import com.bbs.service.Announcement.AnnouncementInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Service
public class AnnouncementInfoServiceImpl implements AnnouncementInfoService {

    @Resource
    private AnnouncementInfoDao announcementInfoDao;

    @Resource
    private PlateInfoDao plateInfoDao;

    @Resource
    private DistrictInfoDao districtInfoDao;

    @Resource
    private UserBaseInfoDao userBaseInfoDao;

    @Override
    public AnnouncementInfo getRecentAnnouncement(int plate_id, int district_id) throws Exception {
        if (plate_id == 0 & district_id == 0) {
            return announcementInfoDao.getRecentAnnouncement(plate_id, district_id);
        } else if (plate_id != 0) {
            List<AnnouncementInfo> ls = announcementInfoDao.getAnnounceInfosByPlateId(plate_id);
            if (ls.size() > 0)
                return ls.get(0);
        } else {
            List<AnnouncementInfo> ls = announcementInfoDao.getAnnounceInfosBydistrictId(district_id);
            if (ls.size() > 0)
                return ls.get(0);
        }
        return null;
    }


    @Override
    public void addAnnouncementInfo(AnnouncementInfo announcementInfo) throws Exception {
        announcementInfo.setPost_time(new Date());
        if (announcementInfo.getPlate_id() == 0 & announcementInfo.getDistrict_id() == 0) {
            announcementInfoDao.addAnnouncementInfo(announcementInfo);
        } else if (announcementInfo.getPlate_id() != 0) {
            announcementInfoDao.addPlateAnnInfo(announcementInfo);
        } else
            announcementInfoDao.addDisAnnInfo(announcementInfo);
    }

    @Override
    public PageInfo<AnnouncementInfo> getAnnounceInfos(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        List<AnnouncementInfo> ls = announcementInfoDao.getAnnounceInfos();
        for (AnnouncementInfo info : ls) {
            info.setUserBaseInfo(userBaseInfoDao.getUserBaseInfoByUserId(info.getOwner()));
            if (info.getPlate_id() != -1 & info.getDistrict_id() == -1) {
                info.setPlate_name(plateInfoDao.getPlateInfo(info.getPlate_id()).getPlate_name());
            }
            if (info.getPlate_id() == -1 & info.getDistrict_id() != -1) {
                info.setPlate_name(plateInfoDao.getPlateInfo(districtInfoDao.getDistrictInfo(info.getDistrict_id()).getPlate_id()).getPlate_name());
                info.setDistrict_name(districtInfoDao.getDistrictInfo(info.getDistrict_id()).getDistrict_name());
            }
        }
        PageInfo<AnnouncementInfo> pageInfoDemo = new PageInfo<AnnouncementInfo>(ls);
        return pageInfoDemo;
    }

    @Override
    public PageInfo<AnnouncementInfo> getAnnounceInfos(int plate_id, int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        List<AnnouncementInfo> ls2 = new LinkedList<>();
        if (plate_id == -1) {
            ls2 = announcementInfoDao.getAnnInfos();
        } else
            ls2 = announcementInfoDao.getAnnounceInfosByPlateId(plate_id);
        for (AnnouncementInfo info : ls2) {
            info.setUserBaseInfo(userBaseInfoDao.getUserBaseInfoByUserId(info.getOwner()));
        }
        PageInfo<AnnouncementInfo> pageInfoDemo = new PageInfo<AnnouncementInfo>(ls2);

        return pageInfoDemo;
    }


    @Override
    public PageInfo<AnnouncementInfo> getAnnounceInfos2(int district_id, int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        List<AnnouncementInfo> ls2 = announcementInfoDao.getAnnounceInfosBydistrictId(district_id);
        for (AnnouncementInfo info : ls2) {
            info.setUserBaseInfo(userBaseInfoDao.getUserBaseInfoByUserId(info.getOwner()));
        }
        PageInfo<AnnouncementInfo> pageInfoDemo = new PageInfo<AnnouncementInfo>(ls2);

        return pageInfoDemo;
    }

    @Override
    public void batchDelete(List<AnnouncementInfo> ls) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (AnnouncementInfo info : ls) {
            sb.append("'").append(info.getId()).append("'").append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        String s = sb.toString();
        announcementInfoDao.batchDelete(s);
    }

    @Override
    public PageInfo<AnnouncementInfo> getAnnInfos(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        List<AnnouncementInfo> ls2 = announcementInfoDao.getAnnInfos();
        for (AnnouncementInfo info : ls2) {
            info.setUserBaseInfo(userBaseInfoDao.getUserBaseInfoByUserId(info.getOwner()));
        }
        PageInfo<AnnouncementInfo> pageInfoDemo = new PageInfo<AnnouncementInfo>(ls2);

        return pageInfoDemo;
    }

    @Override
    public PageInfo<AnnouncementInfo> getAnnByColumName(String colum_name, int value, int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        List<AnnouncementInfo> ls2 = announcementInfoDao.getAnnByColumName(colum_name, value);
        for (AnnouncementInfo info : ls2) {
            info.setUserBaseInfo(userBaseInfoDao.getUserBaseInfoByUserId(info.getOwner()));
        }
        PageInfo<AnnouncementInfo> pageInfoDemo = new PageInfo<AnnouncementInfo>(ls2);

        return pageInfoDemo;
    }
}
