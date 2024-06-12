package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关注
 *
 * @author Cc
 * 2024-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    private String Fid;
    /**
     * 关注 者
     */
    private String Follower;
    /**
     * 被追随者
     */
    private String Followee;
    /**
     * 关注状态 0为正常 1为取消
     */
    private int Fstatus;
}
