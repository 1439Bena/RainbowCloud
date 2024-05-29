package com.controller.user.post;

import com.bean.AccountInfo;
import com.bean.Post;
import com.controller.base.BaseController;
import com.service.PostService;
import com.utils.RandomUtils;
import com.utils.impl.VideoUtils;
import com.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Cc
 * 2023-12-25
 */
@RestController
public class PostController extends BaseController {
    @Autowired
    private PostService service;

    @RequestMapping("/GetPost")
    protected String GetPost(@RequestParam(value = "key", required = false) String key, @RequestParam("page") int page) {

        List<Post> posts = service.getPost(page, 5);

        if (posts != null) {
            Long count = service.getPostCount();

            return print(new PageVo(200, "success", posts, count));
        }
        return print(errorJson(999, "服务器炸了", null));
    }

    @RequestMapping("/ViewPost")
    protected String ViewPost(@RequestParam("pid") String pid) {

        Post post = service.postInfo(pid);

        return print(successJson(post));
    }

    @RequestMapping("UploadVideo")
    protected String UploadVideo(HttpServletRequest request) throws ServletException, IOException {
        String url = new VideoUtils().StoreVideo(request, "wangeditor-uploaded-video");

        return pring(successUploadVideo(url));
    }

    @RequestMapping("/PubPost")
    protected String PubPost(HttpServletRequest request) throws Exception {
        final String undefined = "undefined";

        String pcontent = request.getParameter("pcontent");
        String publisheruid = request.getParameter("publisheruid");
        String image = request.getParameter("image");
        String videourl = request.getParameter("videourl");

        Post post = new Post();
        post.setPid("Post_" + RandomUtils.GetRandomNumber(8, 0, 9));
        post.setPcontent(pcontent);
        if (image != null && !undefined.equals(image)) {
            post.setPimage(image);
        }
        if (videourl != null && !undefined.equals(videourl)) {
            post.setPvideo(videourl);
        }
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUid(publisheruid);
        post.setPublishaccountInfo(accountInfo);
        post.setPtime(new Date());
        post.setPstatus("正常");

        int row = service.pubPost(post);
        if (row > 0) {
            return print(successJson(null));
        } else {
            return print(errorJson());
        }
    }
    @RequestMapping("/PubCommentOrReply")
    protected String PubCommentOrReply(HttpServletRequest request) throws Exception {
        String pcontent = request.getParameter("pcontent");
        String publisheruid = request.getParameter("publisheruid");
        String parentId = request.getParameter("parentId");

        Post post = new Post();
        post.setPid("Post_" + RandomUtils.GetRandomNumber(8, 0, 9));
        post.setPcontent(pcontent);
        post.setParentid(parentId);

        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUid(publisheruid);
        post.setPublishaccountInfo(accountInfo);
        post.setPtime(new Date());
        post.setPstatus("正常");

        int row = service.pubPost(post);
        if (row > 0) {
            return print(successJson(null));
        } else {
            return print(errorJson());
        }
    }
}
