package com.bbs.dao.Post;

import com.bbs.model.Post.DistrictInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DistrictInfoDao {

    @Select("select * from DistrictInfo where plate_id = #{plate_id}")
    List<DistrictInfo> getDistricts(int plate_id) throws Exception;

    @Select("select * from DistrictInfo where id = #{id}")
    DistrictInfo getDistrictInfo(int id) throws Exception;

    @Insert("INSERT INTO DistrictInfo(plate_id,district_name) VALUES (#{plate_id},#{district_name})")
    void addDistrictInfo(int plate_id,String district_name) throws Exception;

    @Update("UPDATE DistrictInfo set district_name = #{district_name} WHERE id = #{id}")
    void updateDistrictInfo(DistrictInfo districtInfo) throws Exception;

    @Delete("DELETE FROM DistrictInfo WHERE id = #{id}")
    void deleteDistrictInfo(int id) throws Exception;
}
