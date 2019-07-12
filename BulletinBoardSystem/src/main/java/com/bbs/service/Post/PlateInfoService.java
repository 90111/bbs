package com.bbs.service.Post;

import com.bbs.model.Post.PlateInfo;

import java.util.List;


public interface PlateInfoService {

    List<PlateInfo> getPlates() throws Exception;

    PlateInfo getPlateInfo(int district_id) throws Exception;
}
