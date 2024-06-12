package com.mapper;

import com.bean.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Cc
 * 2024-05-23
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 添加用户信息
     *
     * @param userInfo 用户信息
     * @return int
     */
    int addUserInfo(UserInfo userInfo);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @return int
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 按页面选择用户信息
     *
     * @param userInfo 用户信息
     * @param page     页
     * @param limit    限制
     * @return {@link List }<{@link UserInfo }>
     */
    List<UserInfo> selectUserInfoByPage(@Param("userInfo") UserInfo userInfo, @Param("page") int page, @Param("limit") int limit);

    /**
     * 按页数选择
     *
     * @param userInfo 用户信息
     * @return {@link Long }
     */
    Long selectByPageCount(@Param("userInfo") UserInfo userInfo);

    /**
     * 查询某个用户信息
     *
     * @param useruid 用户id
     * @return {@link UserInfo }
     */
    UserInfo selectSomeOneUserInfo(@Param("useruid") String useruid);
}