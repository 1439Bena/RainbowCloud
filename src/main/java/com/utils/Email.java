package com.utils;

/**
 * 电子邮件
 *
 * @author CC
 * 2024/05/23
 */
public interface Email {

    /**
     * 获取验证码
     *
     * @return {@link String }
     */
    String getCap();

    /**
     * 发送电子邮件
     *
     * @param email
     * @param content 内容
     */
    void sendEmail(String email, String content);
}
