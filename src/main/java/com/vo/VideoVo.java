package com.vo;

import lombok.Data;

/**
 * @author Cc
 * 2024-01-04
 */
@Data
public class VideoVo {
    private String url;

    public VideoVo(String url) {
        this.url = url;
    }
}
