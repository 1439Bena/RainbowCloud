package com.controller.user.post;

import com.bean.PostInfo;
import com.controller.base.BaseController;
import com.service.PostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cc
 * 2024-05-24
 */
@RestController
public class PostInfoController extends BaseController {
    @Autowired
    private PostInfoService service;
    @RequestMapping("/GetLikeAndComment")
    protected String GetLikeAndComment(@RequestParam("pid") String pid, @RequestParam("uid") String uid) {

        PostInfo postInfo = service.selectLikesAndCommentsCount(pid, uid);

        if (postInfo != null){
            return print(successJson(postInfo));
        }
        return print(errorJson(999, "服务器炸了", null));
    }
}
