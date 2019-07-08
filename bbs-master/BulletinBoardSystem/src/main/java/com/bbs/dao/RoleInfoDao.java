package com.bbs.dao;

import com.bbs.model.User.FunctionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleInfoDao {

    @Select("select * from FunctionInfo where id in (select function_info_id from RoleFunctionPermission where role_info_id = #{role_info_id})")
    public List<FunctionInfo> getFunctions (int role_info_id) throws Exception;
}
