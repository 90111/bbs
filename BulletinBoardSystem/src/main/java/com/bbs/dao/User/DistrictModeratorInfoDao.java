package com.bbs.dao.User;


import com.bbs.model.User.DistrictModeratorInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DistrictModeratorInfoDao {

    @Insert("insert into DistrictModeratorInfo (user_id,plate_id) values(#{user_id},#{plate_id})")
    void addModerInfo(DistrictModeratorInfo districtModeratorInfo) throws Exception;

    @Insert("insert into DistrictModeratorInfo (user_id,plate_id,district_id) values(#{user_id},#{plate_id},#{district_id})")
    void addDisInfo(DistrictModeratorInfo districtModeratorInfo) throws Exception;


    @Update("update DistrictModeratorInfo set plate_id=#{plate_id}, district_id=#{district_id} where user_id=#{user_id}")
    void updateInfo(DistrictModeratorInfo districtModeratorInfo) throws Exception;

    @Delete("delete from DistrictModeratorInfo where ${colum_name}=#{id} and user_id=#{user_id}")
    void deleteInfo(String colum_name, int user_id, int id) throws Exception;

    @Delete("delete from DistrictModeratorInfo where plate_id=#{plate_id}")
    void deleteInfoByPlateId(int plate_id) throws Exception;

    @Select("select * from DistrictModeratorInfo where ${colum_name}=#{s}")
    List<DistrictModeratorInfo> getDisInfo(String colum_name, int s) throws Exception;

    @Select("select * from DistrictModeratorInfo where ${colum_name}=#{s} and district_id is null")
    List<DistrictModeratorInfo> getModeratorInfo(String colum_name, int s) throws Exception;

    @Select("select * from DistrictModeratorInfo where user_id=#{s} and district_id is not null")
    List<DistrictModeratorInfo> getUserDis(int s) throws Exception;

    @Select("select * from DistrictModeratorInfo where ${colum_name}=#{s}")
    List<DistrictModeratorInfo> getDisModerInfosById(String colum_name, int s) throws Exception;
}
