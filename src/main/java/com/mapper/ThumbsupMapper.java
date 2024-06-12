package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cc
 * 2024-05-23
 */
@Mapper
public interface ThumbsupMapper {
    /**
     * 查询 一个用户点赞的所有帖子
     *
     * @param tUserId 用户id
     * @return {@link List }<{@link String }>
     */
    List<String> selectLikesPost(String tUserId);
    /**
     * 查询 “点赞计数”
     *
     * @param totalPid 对应用户所有 pid
     * @return long
     */
    long selectLikeCount(List<String> totalPid);

    /**
     * 检查喜欢
     *
     * @param pid 帖子Id
     * @param uid UID 接口
     * @return int
     */
    int checkLiked(@Param("pid") String pid, @Param("uid") String uid);

    /**
     * 添加赞
     *
     * @param tid 潮汐
     * @param pid 帖子Id
     * @param uid UID 接口
     * @return int
     */
    int addLike(@Param("tid") String tid, @Param("pid") String pid, @Param("uid") String uid);

    /**
     * 取消喜欢
     *
     * @param pid 帖子Id
     * @param uid UID 接口
     * @return int
     */
    int cancelLike(@Param("pid") String pid, @Param("uid") String uid);

    /**
     * 恢复点赞
     *
     * @param pid 帖子Id
     * @param uid UID 接口
     * @return int
     */
    int recoverLike(@Param("pid") String pid, @Param("uid") String uid);
}