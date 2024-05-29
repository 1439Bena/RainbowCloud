package com.controller.admin.userinfo;

import com.bean.UserInfo;
import com.controller.base.BaseController;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Cc
 * 2023-12-06
 */
@RestController
public class UserInfoController extends BaseController {
    @Autowired
    private UserInfoService service;
    @RequestMapping("/GetUser")
    protected String GetUserInfo(@RequestParam(value = "nickname", required = false) String nickname, @RequestParam(value = "gender", required = false) String gender, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(nickname);
        userInfo.setGender(gender);

        List<UserInfo> users = service.getUserInfo(userInfo, page, limit);
        Long count = service.getUserCount(userInfo);

        return print(pageVo(count, users));
    }

    @RequestMapping("/UpdateUserInfo")
    protected String UpdateUserInfo(@RequestParam("useruid") String useruid, @RequestParam("nickname") String nickname, @RequestParam("gender") String gender, @RequestParam("birthday") String birthday, @RequestParam("location") String location, @RequestParam("bio") String bio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate bir = LocalDate.parse(birthday, formatter);

        UserInfo userInfo = new UserInfo(useruid, nickname, gender, bir, location, bio);

        int row = service.updateUser(userInfo);

        if (row > 0) {
            return print(successJson(null));
        } else {
            return print(errorJson());
        }
    }
}
