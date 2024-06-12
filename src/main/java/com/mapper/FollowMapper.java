package com.mapper;

import com.bean.Follow;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Cc
 * 2024-06-03
 */
@Mapper
public interface FollowMapper {
    /**
     * 关注一个账号
     *
     * @param follow 跟随
     * @return int
     */
    int insertFollow(Follow follow);

    /**
     * 检查 是否 关注
     *
     * @param Follower 追随 者
     * @param Followee 被追随者
     * @return int
     */
    int checkFollow(String Follower, String Followee);

    /**
     * 取消关注
     *
     * @param Follower 追随 者
     * @param Followee 被追随者
     * @return int
     */
    int unFollow(String Follower, String Followee);

    /**
     * 恢复关注
     *
     * @param Follower 追随 者
     * @param Followee 被追随者
     * @return int
     */
    int recoverFollow(String Follower, String Followee);

    /**
     * 查询一个账号的粉丝数
     *
     * @param Followee 追随者
     * @return long
     */
    long selectFollowee(String Followee);

    /**
     * 查询一个账号的关注数
     *
     * @param Follower 追随 者
     * @return long
     */
    long selectFollower(String Follower);
}
