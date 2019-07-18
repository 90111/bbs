package com.bbs.service.User;

import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.model.User.UserBaseInfo;

import java.util.List;

public interface DistrictModeratorInfoService {

    void addModerInfo(DistrictModeratorInfo districtModeratorInfo, int role) throws Exception;

    void addDisInfo(DistrictModeratorInfo districtModeratorInfo, int role) throws Exception;

    void deleteInfo(String colum_name, int user_id, int id) throws Exception;

    void updateInfo(DistrictModeratorInfo districtModeratorInfo) throws Exception;

    List<DistrictModeratorInfo> getInfo(String colum_name, int district_id) throws Exception;

    List<DistrictModeratorInfo> getDisModerInfosById(String colum_name, int user_id) throws Exception;

    List<DistrictModeratorInfo> getUserDis(int user_id) throws Exception;

    List<DistrictModeratorInfo> getModeratorInfo(String colum_name, int plate_id) throws Exception;

    void deleteInfoByPlateId(int plate_id) throws Exception;

}
