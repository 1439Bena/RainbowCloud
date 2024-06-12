package com.service;

import com.bean.Post;
import com.mapper.PostMapper;
import com.mapper.ThumbsupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cc
 * 2023-12-05
 */
@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private ThumbsupMapper thumbsupMapper;

    public List<Post> getPost(Integer page, Integer limit) {
        return postMapper.selectByPage(page, limit);
    }

    public List<Post> getComments(String cparentid) {
        return postMapper.selectComments(cparentid);
    }

    public long getPostCount() {
        return postMapper.selectByPageCount();
    }

    public Post postInfo(String pid) {
        return postMapper.selectPostAndComment(pid);
    }

    public int pubPost(Post post) {
        return postMapper.add(post);
    }

    public int pubCommentAndReply(Post commentOrReply) {
        return postMapper.add(commentOrReply);
    }

    public List<Post> getSomeOnePost(String publisher) {
        return postMapper.selectPostByAccount(publisher);
    }

    public long selectPostCount(String uid) {
        return postMapper.selectTotalPost(uid);
    }

    public Post RecentPost(String pid) {
        return postMapper.selectRecentPost(pid);
    }

    public List<Post> getSomeOneComment(String publisher) {
        return postMapper.selectCommentParent(publisher);
    }

    public List<Post> getLikesPost(String tUserId) {
        List<String> pIds = thumbsupMapper.selectLikesPost(tUserId);

        return postMapper.selectLikesPost(pIds);
    }
}
