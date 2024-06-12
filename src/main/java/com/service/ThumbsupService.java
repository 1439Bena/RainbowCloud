package com.service;

import com.mapper.PostMapper;
import com.mapper.ThumbsupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 竖起大拇指服务
 *
 * @author CC
 * @date 2024/05/23
 */
@Service
public class ThumbsupService {
    @Autowired
    private ThumbsupMapper thumbsupMapper;
    @Autowired
    private PostMapper postMapper;

    public List<String> selectLikesPost(String tUserId) {
        return thumbsupMapper.selectLikesPost(tUserId);
    }

    public long selectLikeCount(String uid) {
        List<String> totalPid = postMapper.selectTotalPid(uid);

        return thumbsupMapper.selectLikeCount(totalPid);
    }

    public boolean checkLiked(String pid, String uid) {
        return thumbsupMapper.checkLiked(pid, uid) == 1;
    }

    public boolean addLike(String tid, String pid, String uid) {
        return thumbsupMapper.addLike(tid, pid, uid) == 1;
    }

    public boolean cancelLike(String pid, String uid) {
        return thumbsupMapper.cancelLike(pid, uid) == 1;
    }

    public boolean recoverLike(String pid, String uid) {
        return thumbsupMapper.recoverLike(pid, uid) == 1;
    }
}