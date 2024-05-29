package com.vo;

import lombok.Data;

/**
 * @author Cc
 * 2024-01-04
 */
@Data
public class VideoRes {
    private int errno;
    private Object data;

    public VideoRes(int i, Object videoVo) {
        this.errno = i;
        this.data = videoVo;
    }
}
