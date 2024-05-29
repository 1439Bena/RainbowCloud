package com.service;

import com.bean.Post;
import com.mapper.PostMapper;
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

    public int pubCommentAndReply(Post commentOrReply) { return postMapper.add(commentOrReply); }
}
