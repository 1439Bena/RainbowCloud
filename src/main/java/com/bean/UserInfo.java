package com.bean;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author CC
 * 2024/05/23
 */
@Getter
@Setter
public class UserInfo {
    private String useruid;
    private String nickname;
    private String gender;
    private LocalDate birthday;
    private String location;
    private String bio;

    public UserInfo() {
    }

    public UserInfo(String useruid, String nickname) {
        this.useruid = useruid;
        this.nickname = nickname;
    }

    public UserInfo(String useruid, String nickname, String gender, LocalDate birthday, String location, String bio) {
        this.useruid = useruid;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.location = location;
        this.bio = bio;
    }
}
