package com.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 视频
 *
 * @author CC
 * @date 2024/05/23
 */
public interface Video {
    /**
     * 商店视频
     *
     * @param request 请求
     * @param file    文件
     * @return {@link String }
     * @throws ServletException Servlet 异常
     * @throws IOException      io异常
     */
    String StoreVideo(HttpServletRequest request, String file) throws ServletException, IOException;

    /**
     * 视频转换字节
     *
     * @param videoPath 视频路径
     * @return {@link byte[] }
     * @throws IOException io异常
     */
    byte[] VideoConvertByte(String videoPath) throws IOException;
}
