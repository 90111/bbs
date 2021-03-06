package com.bbs.service.Post;

import com.bbs.model.Post.DistrictInfo;

import java.util.List;


public interface DistrictInfoService {

    List<DistrictInfo> getDistrictInfos(int plate_id) throws Exception;

    DistrictInfo getDistrictInfo(int district_id) throws Exception;

    void addDistrictInfo(int id,String name) throws Exception;

    void updateDistrictInfo(DistrictInfo districtInfo) throws Exception;

    void deleteDistrictInfo(int id) throws Exception;

    DistrictInfo getDistrictByPlateAndName(int plate_id, String name) throws Exception;

    int getDistrictNum() throws Exception;
}
