package com.bbs.dao.Post;

import com.bbs.model.Post.DistrictInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DistrictInfoDao {

    @Select("select * from DistrictInfo")
    public List<DistrictInfo> getDistricts() throws Exception;
}
