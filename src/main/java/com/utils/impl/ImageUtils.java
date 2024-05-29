package com.utils.impl;

import com.utils.Image;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author Cc
 * 2023-10-2023
 */
public class ImageUtils implements Image {

    @Override
    public byte[] partToBytes(HttpServletRequest request, String file) throws ServletException, IOException {
        byte[] buffer = new byte[1024];
        Part imagePart = request.getPart(file);
        try {
            InputStream inputStream = imagePart.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String byteToString(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }

    @Override
    public byte[] stringToByte(String imageString) {
        return Base64.getDecoder().decode(imageString);
    }
}
