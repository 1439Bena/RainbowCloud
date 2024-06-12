package com.controller.admin;


import com.bean.AccountInfo;
import com.bean.UserInfo;
import com.controller.admin.pojo.AccountInfoData;
import com.controller.base.BaseController;
import com.service.AccountInfoService;
import com.service.UserInfoService;
import com.utils.RandomUtils;
import com.utils.impl.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Cc
 * 2023-12-01
 */
@RestController
public class AccountController extends BaseController {
    @Autowired
    private AccountInfoService service;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/AddAccount")
    protected String AddAccount(@RequestBody AccountInfoData data) {
        try {

            AccountInfo accountInfo = new AccountInfo(data.getUid(), data.getUsername(), data.getPassword(), data.getAvatar(), data.getEmail(), data.getAphone(), null, 0, null);

            accountInfo.setUid("Rb_" + RandomUtils.GetRandomNumber(8, 0, 9));
            accountInfo.setAstatus(0);
            accountInfo.setRegistrationtime(LocalDateTime.now());
            int row = service.addAccount(accountInfo);
            UserInfo userInfo = new UserInfo();
            userInfo.setUseruid(accountInfo.getUid());
            userInfo.setNickname("用户_" + RandomUtils.GetRandomNumber(8, 0, 9));
            userInfoService.addUser(userInfo);
            if (row > 0) {
                return print(successJson(null));
            } else {
                return print(errorJson(500, "error", null));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/GetAccount")
    protected String GetAccount(@RequestParam(value = "likeusername", required = false) String username, @RequestParam(value = "likephone", required = false) String phone, @RequestParam(value = "likeemail", required = false) String email, @RequestParam("page") int page, @RequestParam("limit") int limit) {

        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUsername(username);
        accountInfo.setAphone(phone);
        accountInfo.setEmail(email);

        List<AccountInfo> accounts = service.getAccountInfo(accountInfo, page, limit);
        Long count = service.getAccountInfoCount(accountInfo);

        return print(pageVo(count, accounts));
    }

    @PostMapping("/UpdateAccount")
    protected String UpdateAccount(@RequestBody AccountInfoData data) {

        AccountInfo accountInfo = new AccountInfo(data.getUid(), data.getUsername(), data.getPassword(), data.getAvatar(), data.getEmail(), data.getAphone(), null, 0, null);

        int row = service.updateAccountInfo(accountInfo);
        if (row > 0) {
            return print(successJson(null));
        } else {
            return print(errorJson());
        }
    }

    @RequestMapping("/UpdateStatu")
    protected String UpdateStatu(@RequestParam("uid") String uid, @RequestParam("statu") int statu) {

        int row = service.updateStatus(uid, statu);
        if (row > 0) {
            return print(successJson(null));
        } else {
            return print(errorJson());
        }
    }

    @RequestMapping("/UploadAvatar")
    protected String UploadAvatar(HttpServletRequest request) throws IOException, ServletException {
        byte[] avatar = new ImageUtils().partToBytes(request, "head");
        String head = new ImageUtils().byteToString(avatar);
        return print(successJson(head));
    }

    @RequestMapping("/{uid}")
    public String getSomeOneAccount(@PathVariable("uid") String uid){
        AccountInfo accountInfo = service.selectSomeOneAccount(uid);

        return print(successJson(accountInfo));
    }
}
