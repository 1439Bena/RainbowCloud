package com.service;

import com.bean.Follow;
import com.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cc
 * 2024-06-03
 */
@Service
public class FollowService {
    @Autowired
    private FollowMapper mapper;

    /**
     * 关注某个人
     *
     * @param follow 跟随
     * @return int
     */
    public boolean FollowSomeOne(Follow follow) {
        return mapper.insertFollow(follow) == 1;
    }

    /**
     * 检查关注
     *
     * @param Follower 追随 者
     * @param Followee 追随者
     * @return boolean
     */
    public boolean checkFollow(String Follower, String Followee) {
        return mapper.checkFollow(Follower, Followee) == 1;
    }

    /**
     * 取消关注
     *
     * @param Follower 追随 者
     * @param Followee 追随者
     * @return boolean
     */
    public boolean cancelFollow(String Follower, String Followee) {
        return mapper.unFollow(Follower, Followee) == 1;
    }

    /**
     * 恢复关注
     *
     * @param Follower 追随 者
     * @param Followee 追随者
     * @return boolean
     */
    public boolean recoverFollow(String Follower, String Followee) {
        return mapper.recoverFollow(Follower, Followee) == 1;
    }

    /**
     * 查询粉丝
     *
     * @param Followee 追随者
     * @return long
     */
    public long selectFollowee(String Followee) {
        return mapper.selectFollowee(Followee);
    }

    /**
     * 查询关注
     *
     * @param Follower 追随 者
     * @return long
     */
    public long selectFollower(String Follower) {
        return mapper.selectFollower(Follower);
    }
}
