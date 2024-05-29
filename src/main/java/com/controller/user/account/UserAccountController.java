package com.controller.user.account;

import com.bean.AccountInfo;
import com.bean.UserInfo;
import com.controller.base.BaseController;
import com.service.AccountInfoService;
import com.service.UserInfoService;
import com.utils.RandomUtils;
import com.utils.impl.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author Cc
 * 2023-12-28
 */
@RestController
public class UserAccountController extends BaseController {
    @Autowired
    private AccountInfoService service;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/GetCap")
    protected String GetCap(@RequestParam("email") String email, HttpSession session) {
        String cap = new EmailUtils().getCap();
        String content = "亲爱的用户：\n\n\n您好！感谢您使用服务，您正在进行邮箱验证，本次请求的验证码为：" + cap + "\n\n\nRb-Cloud账号团队";
        new EmailUtils().sendEmail(email, content);
        session.setAttribute("cap", cap);
        return print(successJson(cap));
    }

    @RequestMapping("/SignUpAccount")
    protected String SignUpAccount(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("cap") String cap, @RequestParam("password") String password, @RequestParam("servercap") String servercap) throws Exception {
        AccountInfo accountInfo = new AccountInfo();


        if (cap == null || !cap.equals(servercap)) {
            return print(errorJson(401, "验证码错误", null));
        } else {
            accountInfo.setUid("Rb_" + RandomUtils.GetRandomNumber(8, 0, 9));
            accountInfo.setUsername(username);
            accountInfo.setEmail(email);
            accountInfo.setPassword(password);
            accountInfo.setAstatus(0);
            accountInfo.setRegistrationtime(LocalDateTime.now());

            int row = service.signUpAccount(accountInfo);
            UserInfo userInfo = new UserInfo();
            userInfo.setUseruid(accountInfo.getUid());
            userInfo.setNickname("用户_" + RandomUtils.GetRandomNumber(8, 0, 9));
            userInfoService.addUser(userInfo);
            if (row > 0) {
                return print(successJson(null));
            } else {
                return print(errorJson(500, "error", null));
            }
        }
    }

    @RequestMapping("/SignInAccount")
    protected String SignIn(@RequestParam("username") String username, @RequestParam("password") String password) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUsername(username);
        accountInfo.setPassword(password);

        AccountInfo account = service.signInAccount(accountInfo);

        if (account == null) {
            return print(errorJson(401, "用户名或密码错误!", null));
        } else if (account.getAstatus() != 0) {
            return print(errorJson(444, "该账号已被封禁!", null));
        } else {
            return print(successJson(account));
        }
    }
}
