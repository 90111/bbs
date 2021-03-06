package com.bbs.dao.Post;

import com.bbs.model.Post.PlateInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Component
public interface PlateInfoDao {

    @Select("select * from PlateInfo order by id")
    List<PlateInfo> getPlates() throws Exception;

    @Select("select * from PlateInfo where id in (${s})")
    List<PlateInfo> getPlatesById(@Param("s") String s) throws Exception;

    @Select("select * from PlateInfo where id=#{id}")
    PlateInfo getPlateInfo(int id) throws Exception;

    @Select("select * from PlateInfo where plate_name=#{name}")
    PlateInfo getPlateInfoByName(String name) throws Exception;

    @Insert("INSERT into PlateInfo(plate_name) VALUES (#{name})")
    void addPlateInfoByName(String name) throws Exception;

    @Update("UPDATE PlateInfo set plate_name = #{plate_name} WHERE id = #{id}")
    void updatePlateInfo(int id, String plate_name) throws Exception;

    @Update("update PlateInfo set post_num = (SELECT sum(DistrictInfo.post_num) from DistrictInfo WHERE plate_id=#{id}) where id=#{id}")
    void updatePlatePostNum(int id) throws Exception;

    @Delete("DELETE FROM PlateInfo WHERE id = #{id}")
    void deletePlateInfoById(int id) throws Exception;

    @Select("select count(*) from PlateInfo")
    int getPlateNum() throws Exception;

}
