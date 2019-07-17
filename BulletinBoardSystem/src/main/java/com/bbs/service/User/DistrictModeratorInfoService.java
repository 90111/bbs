package com.bbs.service.User;

import com.bbs.model.User.DistrictModeratorInfo;
import com.bbs.model.User.UserBaseInfo;

import java.util.List;

public interface DistrictModeratorInfoService {

    void addInfo(DistrictModeratorInfo districtModeratorInfo, int role) throws Exception;

    void deleteInfo(String colum_name, int user_id, int id) throws Exception;

    void updateInfo(DistrictModeratorInfo districtModeratorInfo) throws Exception;

    List<UserBaseInfo> getInfo(int plate_id, int district_id) throws Exception;
}
