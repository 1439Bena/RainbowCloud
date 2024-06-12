package com.controller.user.userinfo;

import com.bean.UserInfo;
import com.controller.base.BaseController;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cc
 * 2024-06-04
 */
@RestController
public class UserInfoController extends BaseController {
    @Autowired
    private UserInfoService service;

    @RequestMapping("/GetUserInfo")
    protected String getSomeOneUserInfo(@RequestParam("useruid") String useruid) {
        UserInfo someOneUserInfo = service.getSomeOneUserInfo(useruid);

        return print(successJson(someOneUserInfo));
    }
}
