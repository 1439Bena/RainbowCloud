package com.utils.impl;


import com.utils.Email;
import com.utils.RandomUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * 电子邮件工具
 *
 * @author CC
 * @date 2024/05/23
 */
public class EmailUtils implements Email {

    @Override
    public String getCap() {
        try {
            return RandomUtils.GetRandomNumber(6, 0, 9);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmail(String email, String content) {
        SimpleEmail mail = new SimpleEmail();
        //服务器地址
        mail.setHostName("smtp.163.com");
        //发件地址:邮箱，授权码（"xuyujin3306@163.com", "KSGVHEAKGPOBIZJN"）
        //QQ   smtp:ughmuiftketvbeac   pop3:blbktqbqgppdbbbg   shouquan:dpovvzugjwpfbcg
        mail.setAuthentication("xuyujin3306@163.com", "KSGVHEAKGPOBIZJN");
        //设置内容编码
        mail.setCharset("utf-8");

        try {
            mail.addTo(email);
            //设置发件邮箱
            mail.setFrom("xuyujin3306@163.com");
            //设置标题
            mail.setSubject("Your RainbowCloud launch code");
            mail.setMsg(content);
            mail.setSSL(true);
            mail.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }
}
