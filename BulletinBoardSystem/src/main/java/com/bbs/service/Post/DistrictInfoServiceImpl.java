package com.bbs.service.Post;

import com.bbs.dao.Post.DistrictInfoDao;
import com.bbs.dao.Post.PlateInfoDao;
import com.bbs.model.Post.DistrictInfo;
import com.bbs.model.Post.PlateInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DistrictInfoServiceImpl implements DistrictInfoService {

    @Resource
    private DistrictInfoDao districtInfoDao;

    @Override
    public List<DistrictInfo> getDistricts() throws Exception {
        return districtInfoDao.getDistricts();
    }
}
