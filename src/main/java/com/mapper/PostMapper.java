package com.mapper;

import com.bean.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Cc
 * 2024-05-23
 */
@Mapper
public interface PostMapper {

    /**
     * 新增帖子
     *
     * @param post 帖子信息
     * @return 受影响的行数
     */
    @Insert("INSERT INTO Post(Pid, Pcontent, PImage, Pvideo, Publisher, Parentid, Ptime, Ptags, Pstatus) " +
            "VALUES(#{pid}, #{pcontent}, #{pimage, typeHandler=com.handler.PostPimageTypeHandler}, #{pvideo}, #{publishaccountInfo.uid}, #{parentid}, #{ptime}, #{ptags}, #{pstatus})")
    int add(Post post);

    /**
     * 删除帖子
     *
     * @param pid 帖子ID
     * @return 受影响的行数
     */
    @Update("UPDATE Post SET Pstatus = '删除' WHERE Pid = #{pid}")
    int del(String pid);

    /**
     * 按页面选择
     *
     * @param page 开始
     * @param limit   结束
     * @return {@link List }<{@link Post }>
     */
    @Select({
            "<script>",
            "WITH RandomizedPosts AS (",
            "    SELECT P.Pid, CAST(P.Pcontent AS NVARCHAR(MAX)) AS Pcontent, P.PImage, P.Pvideo, P.Publisher,",
            "           P.Parentid, P.Ptime, P.Ptags, P.Pstatus,",
            "           U.UserUid, U.Nickname, A.Username, A.Avatar,",
            "           ROW_NUMBER() OVER (ORDER BY NEWID()) AS rowNumber",
            "    FROM Post P",
            "    JOIN UserInfo U ON P.Publisher = U.UserUid",
            "    JOIN Accountinfo A ON U.UserUid = A.Uid",
            "    WHERE P.Parentid IS NULL",
            "      AND P.Pstatus = '正常'",
            "      AND P.Pid NOT IN (SELECT TOP 10 A.Pid FROM Post A ORDER BY NEWID())",
            "    GROUP BY P.Pid, CAST(P.Pcontent AS NVARCHAR(MAX)), P.PImage, P.Pvideo, P.Publisher, P.Parentid, P.Ptime, P.Ptags, P.Pstatus, U.UserUid, U.Nickname, A.Username, A.Avatar",
            ")",
            "SELECT *",
            "FROM (",
            "    SELECT *, ROW_NUMBER() OVER (ORDER BY rowNumber) AS rowIndex",
            "    FROM RandomizedPosts",
            ") AS SubQuery",
            "WHERE rowIndex BETWEEN (#{page} - 1) * #{limit} + 1 AND #{page} * #{limit}",
            "ORDER BY rowNumber",
            "</script>"
    })
    @ResultMap("PostResultMap")
    List<Post> selectByPage(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 查询帖子的总数
     *
     * @return 帖子的总数
     */
    @Select("SELECT COUNT(Pid) FROM Post WHERE Parentid IS NULL AND Pstatus = '正常'")
    Long selectByPageCount();

    /**
     * 查询帖子的评论
     *
     * @param cparentid 帖子ID
     * @return 评论列表
     */
    @Select("SELECT * FROM ( " +
            "SELECT Post.*, UserInfo.UserUid, UserInfo.Nickname, Accountinfo.Username, Accountinfo.Avatar, " +
            "ROW_NUMBER() OVER(ORDER BY Pid) AS rownum " +
            "FROM Post " +
            "JOIN UserInfo ON Post.Publisher = UserInfo.UserUid " +
            "JOIN Accountinfo ON Post.Publisher = Accountinfo.Uid " +
            "WHERE Parentid = #{cparentid} AND Pstatus = '正常' " +
            ") AS t")
    @ResultMap("PostResultMap")
    List<Post> selectComments(String cparentid);

    /**
     * 查询单个帖子
     *
     * @param pid 帖子ID
     * @return 帖子信息
     */
    @Select("SELECT * FROM ( " +
            "SELECT Post.*, UserInfo.UserUid, UserInfo.Nickname, Accountinfo.Username, Accountinfo.Avatar, " +
            "ROW_NUMBER() OVER(ORDER BY Pid) AS rownum " +
            "FROM Post " +
            "JOIN UserInfo ON Post.Publisher = UserInfo.UserUid " +
            "JOIN Accountinfo ON Post.Publisher = Accountinfo.Uid " +
            "WHERE Parentid IS NULL AND Pstatus = '正常' AND Pid = #{pid} " +
            ") AS t")
    @ResultMap("PostResultMap")
    Post selectPost(String pid);

    /**
     * 递归获取评论及其回复
     * @param comment 要获取回复的评论
     */
    default void retrieveReplies(Post comment){
        // 获取该评论的回复
        List<Post> replies = selectComments(comment.getPid());

        // 设置回复列表到评论中
        comment.setComments(replies);

        // 递归处理每条回复
        for(Post reply : replies){
            retrieveReplies(reply);
        }
    }

    /**
     * 查询帖子的评论以及回复
     * @param pid 帖子编号
     * @return {@link Post }
     */
    default Post selectPostAndComment(@Param("pid") String pid){
        // 获取帖子信息
        Post post = selectPost(pid);

        // 获取评论列表
        List<Post> comments = selectComments(pid);

        // 将评论列表设置到帖子信息中
        post.setComments(comments);

        // 遍历每条评论，递归获取回复
        for(Post comment : comments){
            retrieveReplies(comment);
        }

        return post;
    }

}