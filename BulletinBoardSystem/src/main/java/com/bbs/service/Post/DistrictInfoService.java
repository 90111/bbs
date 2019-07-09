package com.bbs.service.Post;

import com.bbs.model.Post.DistrictInfo;

import java.util.List;


public interface DistrictInfoService {

    List<DistrictInfo> getDistricts(int plate_id) throws Exception;
}
