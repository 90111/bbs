package com.bbs.service.Post;

import com.bbs.model.Post.DistrictInfo;

import java.util.List;


public interface DistrictInfoService {

    List<DistrictInfo> getDistrictInfos(int plate_id) throws Exception;

    DistrictInfo getgetDistrictInfo(int district_id) throws Exception;

    void addDistrictInfo(int id,String name) throws Exception;

    void updateDistrictInfo(DistrictInfo districtInfo) throws Exception;

    void deleteDistrictInfo(int id) throws Exception;
}
