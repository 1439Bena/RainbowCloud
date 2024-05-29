package com.bean;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author CC
 * 2024/05/23
 */
@Getter
public class AccountInfo {
    private String uid;
    private String username;
    private String password;
    private String avatar;
    private String email;
    private String aphone;
    private UserInfo userinfo;
    private LocalDateTime registrationtime;
    private int astatus;

    public AccountInfo() {
    }

    public AccountInfo(String username, String avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    public AccountInfo(String uid, String username, String password, String avatar, String email, String aphone, LocalDateTime registrationtime, int astatus, UserInfo userinfo) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.aphone = aphone;
        this.userinfo = userinfo;
        this.registrationtime = registrationtime;
        this.astatus = astatus;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }

    public void setRegistrationtime(LocalDateTime registrationtime) {
        this.registrationtime = registrationtime;
    }

    public void setAstatus(int astatus) {
        this.astatus = astatus;
    }
}
