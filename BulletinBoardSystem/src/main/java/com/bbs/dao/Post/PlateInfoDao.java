package com.bbs.dao.Post;

import com.bbs.model.Post.PlateInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PlateInfoDao {

    @Select("select * from PlateInfo")
    public List<PlateInfo> getPlates() throws Exception;

}
