package com.bbs.dao.Post;

import com.bbs.model.Post.PostTitleInfo;
import com.bbs.model.User.UserCollectionInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface PostTitleInfoDao {

    @Insert("insert into PostTitleInfo (districtInfo_id,title,owner,content,post_time, image) VALUES (#{districtInfo_id},#{title},#{owner},#{content},#{post_time}, #{image})")
    public void addPostTitleInfo(PostTitleInfo postTitleInfo) throws Exception;

    @Delete("delete from PostTitleInfo where owner = #{owner} and id=#{id}")
    public void deletePostTitleInfoById(int owner, int id) throws Exception;

    @Update("update PostTitleInfo set reply_num = (select count(*) from ReplyInfo where post_title_id = #{id}) where PostTitleInfo.id = #{id}")
    public void updatePostTitleReplyNum(int id)throws Exception;

    @Update("update PostTitleInfo set like_num=(select count(*) from UserLikeInfo where UserLikeInfo.post_title_id=#{post_title_id}) where id=#{post_title_id}")
    public void updatePostTitleLikeNum(int post_title_id) throws Exception;

    @Update("update PostTitleInfo set recommend_num=(select count(*) from CollectionInfo where CollectionInfo.post_title_id=#{post_title_id}) where id=#{post_title_id}")
    void updatePostTitleRecommendNum(int post_title_id) throws Exception;

    @Update("update PostTitleInfo set view_num = view_num+1 where id=#{id}")
    void updatePostTitleViewNum(int id) throws Exception;

    @Update("update PostTitleInfo set reply_time=#{reply_time} WHERE id=#{id}")
    void updateReplyTime(Date reply_time, int id) throws Exception;

    @Update("update PostTitleInfo set title=#{title},content=#{content},image=#{image},districtInfo_id=#{districtInfo_id},post_time=#{post_time} where id=#{id}")
    void updatePostTitleInfo(PostTitleInfo postTitleInfo) throws Exception;

    @Select("SELECT PostTitleInfo.id as id, title, districtInfo_id, owner, nick_name, PostTitleInfo.image, UserBaseInfo.icon, view_num, PostTitleInfo.like_num, recommend_num, reply_time, reply_num from PostTitleInfo, UserBaseInfo " +
            "where districtInfo_id = #{id} and UserBaseInfo.user_id = PostTitleInfo.owner order by ${orderby} desc")
    List<PostTitleInfo> getPostTitleInfos(int id, @Param("orderby") String orderby) throws Exception;

    @Select("select PostTitleInfo.id as id, districtInfo_id, title, owner, nick_name, content, post_time, PostTitleInfo.image, UserBaseInfo.icon, view_num, PostTitleInfo.like_num, recommend_num,reply_num from PostTitleInfo, UserBaseInfo " +
            "where UserBaseInfo.user_id=PostTitleInfo.owner and PostTitleInfo.id=#{id}")
    PostTitleInfo getPostTitleContent(int id) throws Exception;

    @Select("SELECT PostTitleInfo.id, DistrictInfo.plate_id, DistrictInfo.id as districtInfo_id, title, owner, reply_time, " +
            "UserBaseInfo.nick_name, UserBaseInfo.icon, post_time, recommend_num, view_num, PostTitleInfo.like_num, reply_num, " +
            "PostTitleInfo.image FROM DistrictInfo, PostTitleInfo, UserBaseInfo " +
            "WHERE PostTitleInfo.districtInfo_id = DistrictInfo.id and PostTitleInfo.owner = UserBaseInfo.user_id " +
            "ORDER BY ${orderby} DESC")
    List<PostTitleInfo> getPostTitleInfosByTime(@Param("orderby") String orderby) throws Exception;

    @Select("SELECT id, title, view_num, like_num, image FROM PostTitleInfo WHERE owner=#{user_id} " +
            "ORDER BY `post_time` DESC limit 2")
    List<PostTitleInfo> getUserRecentPostTitleByUserId(int user_id) throws Exception;

    @Select("SELECT id, title, view_num, like_num, image FROM PostTitleInfo WHERE owner=#{user_id} " +
            "ORDER BY `post_time` DESC")
    List<PostTitleInfo> getUserPostTitleByUserId(int user_id) throws Exception;

    @Select("SELECT UserLoginInfo.id as user_id, PostTitleInfo.id as post_title_id, nick_name, icon, title, image, PostTitleInfo.like_num, PostTitleInfo.reply_num, PostTitleInfo.recommend_num, PostTitleInfo.view_num " +
            "from PostTitleInfo JOIN CollectionInfo ON CollectionInfo.post_title_id=PostTitleInfo.id JOIN UserLoginInfo ON PostTitleInfo.`owner` = UserLoginInfo.id  JOIN UserBaseInfo ON UserLoginInfo.id = UserBaseInfo.user_id WHERE CollectionInfo.user_id=#{user_id}")
    List<UserCollectionInfo> getUserCollection(int user_id) throws Exception;

    @Select("select * from PostTitleInfo where id=#{id}")
    PostTitleInfo getPostTitleById(int id) throws Exception;

    @Select("SELECT PostTitleInfo.id, DistrictInfo.plate_id, DistrictInfo.id as districtInfo_id, title, owner, post_time, reply_time, " +
            "UserBaseInfo.nick_name, UserBaseInfo.icon, recommend_num, view_num, PostTitleInfo.like_num, reply_num, " +
            "PostTitleInfo.image FROM DistrictInfo, PostTitleInfo, UserBaseInfo " +
            "WHERE PostTitleInfo.districtInfo_id = DistrictInfo.id and PostTitleInfo.owner = UserBaseInfo.user_id " +
            "and post_time between #{date1} and #{date2}" +
            "ORDER BY view_num DESC limit 20")
    List<PostTitleInfo> getPostTitleBetweenTime(String date1, String date2) throws Exception;

    @Select("SELECT PostTitleInfo.id, DistrictInfo.plate_id, DistrictInfo.id as districtInfo_id, title, owner, post_time, reply_time, " +
            "UserBaseInfo.nick_name, UserBaseInfo.icon, recommend_num, view_num, PostTitleInfo.like_num, reply_num, " +
            "PostTitleInfo.image FROM DistrictInfo, PostTitleInfo, UserBaseInfo " +
            "WHERE PostTitleInfo.districtInfo_id = DistrictInfo.id and PostTitleInfo.owner = UserBaseInfo.user_id " +
            "and state = 4 and PostTitleInfo.districtInfo_id=#{district_id} " +
            "ORDER BY `post_time` DESC limit 20")
    List<PostTitleInfo> getRecommendPostTitles(int district_id) throws Exception;

    @Select("SELECT id, title from PostTitleInfo where title like #{postTitle}")
    List<PostTitleInfo> searchPost(String postTitle) throws Exception;

    @Select("SELECT PostTitleInfo.id, DistrictInfo.plate_id, DistrictInfo.id as districtInfo_id, title, owner, reply_time, " +
            "UserBaseInfo.nick_name, UserBaseInfo.icon, post_time, recommend_num, view_num, PostTitleInfo.like_num, reply_num, " +
            "PostTitleInfo.image FROM DistrictInfo, PostTitleInfo, UserBaseInfo " +
            "WHERE PostTitleInfo.districtInfo_id = DistrictInfo.id and PostTitleInfo.owner = UserBaseInfo.user_id " +
            "and ${colum_name} like #{s}")
    List<PostTitleInfo> getPostTitleInfosByColum(@Param("colum_name") String colum_name, String s) throws Exception;
}
