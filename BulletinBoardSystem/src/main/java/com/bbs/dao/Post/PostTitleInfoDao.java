package com.bbs.dao.Post;

import com.bbs.model.Post.PostTitleInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PostTitleInfoDao {

    @Insert("insert into PostTitleInfo (districtInfo_id,title,owner,content,post_time, image) VALUES (#{districtInfo_id},#{title},#{owner},#{content},#{post_time}, #{image})")
    public void addPostTitleInfo(PostTitleInfo postTitleInfo) throws Exception;

    @Delete("delete from PostTitleInfo where id = #{id}")
    public void deletePostTitleInfoById(int id) throws Exception;

    @Select("select reply_num,reply_time from PostTitleInfo where id = #{id}")
    public void getPostTitleInfoReply(int id) throws Exception;

    @Select("select view_num from PostTitleInfo where id = #{id}")
    public void getPostTitleInfoView(int id) throws Exception;

    @Select("select like_num from PostTitleInfo where id = #{id}")
    public void getPostTitleInfoLike(int id) throws Exception;

    @Select("select recommend_num from PostTitleInfo where id = #{id}")
    public void getPostTitleInfoRecommend(int id) throws Exception;

    @Update("update PostTitleInfo set reply_num = (select count(*) from " +
            "ReplyInfo where post_title_id = #{id}) where PostTitleInfo.id = #{id}")
    public void updatePostTitleInfoReply(PostTitleInfo postTitleInfo)throws Exception;

    @Update("update PostTitleInfo set view_num = #{view_num} where id = #{id}")
    public void updatePostTitleInfoView(PostTitleInfo postTitleInfo)throws Exception;

    @Update("update PostTitleInfo set like_num = #{like_num} where id = #{id}")
    public void updatePostTitleInfoLike(PostTitleInfo postTitleInfo)throws Exception;

    @Update("update PostTitleInfo set recommend_num= #{recommend_num} where id = #{id}")
    public void updatePostTitleInfoRecommend_num(PostTitleInfo postTitleInfo)throws Exception;

    @Select("SELECT PostTitleInfo.id as id, title, owner, nick_name, PostTitleInfo.image, UserBaseInfo.icon, view_num, like_num from PostTitleInfo, UserBaseInfo where districtInfo_id = #{id} and UserBaseInfo.user_id = PostTitleInfo.owner")
    List<PostTitleInfo> getPostTitleInfos(int id) throws Exception;

    @Select("select PostTitleInfo.id as id, title, owner, nick_name, content, post_time, PostTitleInfo.image, UserBaseInfo.icon from PostTitleInfo, UserBaseInfo where UserBaseInfo.user_id=PostTitleInfo.owner and PostTitleInfo.id=#{id}")
    PostTitleInfo getPostTitleContent(int id) throws Exception;

    @Select("SELECT PostTitleInfo.id, DistrictInfo.plate_id, DistrictInfo.id as districtInfo_id, title, owner, UserBaseInfo.nick_name, UserBaseInfo.icon, post_time, reply_time, view_num, like_num, PostTitleInfo.image FROM DistrictInfo, PostTitleInfo, UserBaseInfo WHERE PostTitleInfo.districtInfo_id = DistrictInfo.id and PostTitleInfo.owner = UserBaseInfo.user_id ORDER BY `#{post_time}` DESC limit 20")
    List<PostTitleInfo> getPostTitleInfosByTime(String s) throws Exception;

    @Select("SELECT id, title, view_num, like_num, image FROM PostTitleInfo WHERE owner=#{id} ORDER BY id DESC limit 2")
    List<PostTitleInfo> getUserRecentPostTitleByUserId(int id) throws Exception;
}
