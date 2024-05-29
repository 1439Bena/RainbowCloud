package com.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 图像
 *
 * @author CC
 * @date 2024/05/27
 */
public interface Image {

    /**
     * 部分到字节
     *
     * @param request 请求
     * @param file    文件
     * @return {@link byte[] }
     * @throws ServletException Servlet 异常
     * @throws IOException      io异常
     */
    byte[] partToBytes(HttpServletRequest request, String file) throws ServletException, IOException;

    /**
     * 字节到 Base64 字符串
     *
     * @param imageData 图像数据
     * @return {@link String }
     */
    String byteToString(byte[] imageData);

    /**
     * 字符串 ToByte
     *
     * @param imageString 图像字符串
     * @return {@link byte[] }
     */
    byte[] stringToByte(String imageString);
}
