package com.bbs.service.Post;

import com.bbs.model.Post.PlateInfo;
import com.bbs.model.User.DistrictModeratorInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface PlateInfoService {

    List<PlateInfo> getPlates() throws Exception;

    PlateInfo getPlateInfo(int plate_id) throws Exception;

    PlateInfo getPlateInfoByName(String name) throws Exception;

    void addPlateInfoByName(String name) throws Exception;

    void updatePlateInfo(int id, String name) throws Exception;

    void deletePlateInfoById(int id) throws Exception;

    List<PlateInfo> getPlatesById(List<DistrictModeratorInfo> districtModeratorInfos) throws Exception;

    int getPlateNum() throws Exception;


}
