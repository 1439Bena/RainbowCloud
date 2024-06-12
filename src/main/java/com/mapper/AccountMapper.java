package com.mapper;

import com.bean.AccountInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Cc
 * 2024-05-23
 */
@Mapper
public interface AccountMapper {

    /**
     * 添加账户
     *
     * @param accountInfo 个人信息
     * @return int
     */
    @Insert("INSERT INTO Accountinfo (Uid, Username, Password, Avatar, Email, Aphone, Registrationtime, Astatus) " +
            "VALUES (#{uid}, #{username}, #{password}, #{avatar, typeHandler=com.handler.AccountAvatarTypeHandler}, #{email}, #{aphone}, #{registrationtime}, #{astatus})")
    int addAccount(AccountInfo accountInfo);

    /**
     * 注册账户
     *
     * @param accountInfo 个人信息
     * @return int
     */
    @Insert("INSERT INTO Accountinfo (Uid, Username, Password, Avatar, Email, Aphone, Registrationtime, Astatus) " +
            "VALUES (#{uid}, #{username}, #{password}, #{avatar, typeHandler=com.handler.AccountAvatarTypeHandler}, #{email}, #{aphone}, #{registrationtime}, #{astatus})")
    int signUpAccount(AccountInfo accountInfo);

    /**
     * 登录账户
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link AccountInfo }
     */
    @ResultMap("AccountResultMap")
    AccountInfo signInAccount(@Param("username") String username, @Param("password") String password);

    /**
     * 更新账户
     *
     * @param accountInfo 个人信息
     * @return int
     */
    int updateAccount(AccountInfo accountInfo);

    /**
     * 更新状态
     *
     * @param uid    UID 接口
     * @param status 地位
     * @return int
     */
    int updateAstatus(@Param("uid") String uid, @Param("astatus") int status);

    /**
     * 按页面选择账户
     *
     * @param accountInfo 个人信息
     * @param page        页
     * @param limit       限制
     * @return {@link List }<{@link AccountInfo }>
     */
    @ResultMap("AccountResultMap")
    List<AccountInfo> selectAccountsByPage(@Param("accountInfo") AccountInfo accountInfo, @Param("page") int page, @Param("limit") int limit);

    /**
     * 按页数选择
     *
     * @param accountInfo 个人信息
     * @return {@link Long }
     */
    Long selectByPageCount(@Param("accountInfo") AccountInfo accountInfo);

    /**
     * 查询某个帐户信息
     *
     * @param uid UID 账号id
     * @return {@link AccountInfo }
     */
    AccountInfo selectSomeOneAccount(@Param("uid") String uid);
}
