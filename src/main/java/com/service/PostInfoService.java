package com.service;

import com.bean.PostInfo;
import com.mapper.PostInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cc
 * 2023-12-19
 */
@Service
public class PostInfoService {
    @Autowired
    private PostInfoMapper postInfoMapper;

    public PostInfo selectLikesAndCommentsCount(String pid, String uid) {
        return postInfoMapper.selectPostInfo(pid, uid);
    }
}
