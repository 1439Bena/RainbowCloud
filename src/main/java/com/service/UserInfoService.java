package com.service;

import com.bean.UserInfo;
import com.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cc
 * 2023-12-06
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public int addUser(UserInfo userInfo) {
        return userInfoMapper.addUserInfo(userInfo);
    }

    public List<UserInfo> getUserInfo(UserInfo userInfo, Integer page, Integer limit) {
        return userInfoMapper.selectUserInfoByPage(userInfo, page, limit);
    }

    public Long getUserCount(UserInfo userInfo) {
        return userInfoMapper.selectByPageCount(userInfo);
    }

    public int updateUser(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    public UserInfo getSomeOneUserInfo(String useruid) {
        return userInfoMapper.selectSomeOneUserInfo(useruid);
    }
}
