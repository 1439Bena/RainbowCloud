package com.utils.impl;

import com.utils.Video;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

/**
 * 视频实用程序
 *
 * @author CC
 * @date 2024/05/23
 */
public class VideoUtils implements Video {

    @Override
    public String StoreVideo(HttpServletRequest request, String file) throws ServletException, IOException {
        // 获取上传的文件
        Part filePart = request.getPart(file);
        // 获取文件名
        String fileName = filePart.getSubmittedFileName();
        // 获取文件内容
        InputStream fileContent = filePart.getInputStream();

        String filepath = "video" + "/" + fileName;
        String realpath = request.getServletContext().getRealPath(filepath);
        // 将文件保存到服务器的某个位置
        File targetFile = new File(realpath);
        try (OutputStream outStream = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }
        return filepath;
    }

    @Override
    public byte[] VideoConvertByte(String videoPath) {
        return new byte[0];
    }
}
