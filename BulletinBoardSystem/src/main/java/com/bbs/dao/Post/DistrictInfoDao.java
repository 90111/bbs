package com.bbs.dao.Post;

import com.bbs.model.Post.DistrictInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DistrictInfoDao {

    @Select("select * from DistrictInfo where plate_id = #{plate_id}")
    List<DistrictInfo> getDistricts(int plate_id) throws Exception;

    @Select("select * from DistrictInfo where id = #{id}")
    DistrictInfo getDistrictInfo(int id) throws Exception;
}
