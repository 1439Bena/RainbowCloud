package com.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author CC
 */
@Getter
@Setter
public class Post {
    private String pid;
    private String pcontent;
    private String pimage;
    private String pvideo;
    private AccountInfo publishaccountInfo;
    private UserInfo userinfo;
    private String parentid;
    private Date ptime;
    private String ptags;
    private String pstatus;
    private List<Post> comments;

    public Post() {
    }

    public Post(String pid, String pcontent, String pimage, String pvideo, AccountInfo publishaccountInfo, UserInfo userinfo, String parentid, Date ptime, String ptags, String pstatus, List<Post> comments) {
        this.pid = pid;
        this.pcontent = pcontent;
        this.pimage = pimage;
        this.pvideo = pvideo;
        this.publishaccountInfo = publishaccountInfo;
        this.userinfo = userinfo;
        this.parentid = parentid;
        this.ptime = ptime;
        this.ptags = ptags;
        this.pstatus = pstatus;
        this.comments = comments;
    }
}
