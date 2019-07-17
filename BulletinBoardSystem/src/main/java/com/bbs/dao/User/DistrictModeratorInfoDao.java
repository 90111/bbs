package com.bbs.dao.User;


import com.bbs.model.User.DistrictModeratorInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DistrictModeratorInfoDao {

    @Insert("insert into DistrictModeratorInfo (user_id,plate_id,district_id) values(#{user_id},#{plate_id},#{district_id})")
    void addInfo(DistrictModeratorInfo districtModeratorInfo) throws Exception;

    @Delete("delete from DistrictModeratorInfo where ${colum_name}=#{s}")
    void deleteInfo(String colum_name, int s) throws Exception;

    @Update("update DistrictModeratorInfo set plate_id=#{plate_id}, district_id=#{district_id} where user_id=#{user_id}")
    void updateInfo(DistrictModeratorInfo districtModeratorInfo) throws Exception;

    @Update("update UserLoginInfo, DistrictModeratorInfo set role = 1 where UserLoginInfo.id=DistrictModeratorInfo.user_id and plate_id=#{plate_id}")
    void updateUserRole(int plate_id)throws Exception;

    @Select("select * from DistrictModeratorInfo where district_id=#{district_id}")
    List<DistrictModeratorInfo> getInfo(int plate_id, int district_id) throws Exception;
}
