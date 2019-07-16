package com.bbs.service.Post;

import com.bbs.model.Post.PlateInfo;

import java.util.List;


public interface PlateInfoService {

    List<PlateInfo> getPlates() throws Exception;

    PlateInfo getPlateInfo(int plate_id) throws Exception;

    PlateInfo getPlateInfoByName(String name) throws Exception;

    void addPlateInfoByName(String name) throws Exception;

    void updatePlateInfo(int id, String name) throws Exception;

    void deletePlateInfoById(int id) throws Exception;
}
