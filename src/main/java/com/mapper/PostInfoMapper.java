package com.mapper;

import com.bean.PostInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Cc
 * 2024-05-23
 */
@Mapper
public interface PostInfoMapper {


    /**
     * 点赞计数
     *
     * @param pid PID公司
     * @return long
     */
    @Select("SELECT COUNT(Tid) FROM Thumbsup WHERE Tpostid = #{pid} AND Tstatus = 1")
    long getLikesCount(@Param("pid") String pid);

    /**
     * 按用户获取点赞数
     *
     * @param pid PID公司
     * @param uid UID 接口
     * @return int
     */
    @Select("SELECT COUNT(Tid) FROM Thumbsup WHERE Tpostid = #{pid} AND Tuserid = #{uid} AND Tstatus = 1")
    Integer getLikedCountByUser(@Param("pid") String pid, @Param("uid") String uid);

    /**
     * 获取帖子的点赞数、评论数和点赞状态
     *
     * @param pid 帖子ID
     * @param uid 用户ID
     * @return 帖子信息
     */

    /**
     * 查询帖子的评论数
     *
     * @param pid 帖子ID
     * @return 评论的总数
     */
    @Select("SELECT COUNT(Pid) rows FROM Post WHERE Parentid = #{pid} AND Pstatus = '正常'")
    Long selectCommentsCount(String pid);

    /**
     * 选择帖子信息
     *
     * @param pid PID公司
     * @param uid UID 接口
     * @return {@link PostInfo }
     */
    default PostInfo selectPostInfo(@Param("pid") String pid, @Param("uid") String uid) {
        long likes = getLikesCount(pid);
        boolean isLiked = getLikedCountByUser(pid, uid) > 0;
        long commentsCount = selectCommentsCount(pid);

        return new PostInfo(likes, commentsCount, isLiked);
    }
}