package com.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Cc
 * 2023-12-19
 */
@Getter
@Setter
public class PostInfo {
    private long likes;
    private long commentscount;
    private boolean islike;

    public PostInfo() {
    }

    public PostInfo(long likes, long commentscount, boolean islike) {
        this.likes = likes;
        this.commentscount = commentscount;
        this.islike = islike;
    }
}
