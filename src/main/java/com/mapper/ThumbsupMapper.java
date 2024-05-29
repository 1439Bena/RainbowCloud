package com.mapper;

import org.apache.ibatis.annotations.*;

/**
 * @author Cc
 * 2024-05-23
 */
@Mapper
public interface ThumbsupMapper {

    /**
     * 检查喜欢
     *
     * @param pid PID公司
     * @param uid UID 接口
     * @return int
     */
    int checkLiked(@Param("pid") String pid, @Param("uid") String uid);

    /**
     * 添加赞
     *
     * @param tid 潮汐
     * @param pid PID公司
     * @param uid UID 接口
     * @return int
     */
    int addLike(@Param("tid") String tid, @Param("pid") String pid, @Param("uid") String uid);

    /**
     * 取消喜欢
     *
     * @param pid PID公司
     * @param uid UID 接口
     * @return int
     */
    int cancelLike(@Param("pid") String pid, @Param("uid") String uid);
}