package com.controller.user;

import com.controller.base.BaseController;
import com.service.ThumbsupService;
import com.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cc
 * 2024-01-06
 */
@RestController
public class ThumbsupController extends BaseController {
    @Autowired
    private ThumbsupService service;

    @RequestMapping("/GetLikesCount")
    protected String getLikeCount(@RequestParam("uid") String uid) {
        long count = service.selectLikeCount(uid);

        return print(successJson(count));
    }
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ResponseEntity<String> likePost(@RequestParam("pid") String pid, @RequestParam("uid") String uid) {
        try {
            if (service.checkLiked(pid, uid)) {
                if (service.recoverLike(pid, uid)) {
                    return ResponseEntity.ok("Already liked");
                }
            }

            String tid = "Like-" + RandomUtils.GetRandomNumber(8, 0, 9);
            if (service.addLike(tid, pid, uid)) {
                return ResponseEntity.ok("Like success");
            } else {
                return ResponseEntity.ok("Like failed");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/unlike", method = RequestMethod.POST)
    public ResponseEntity<String> unlikePost(@RequestParam("pid") String pid, @RequestParam("uid") String uid) {
        try {
            if (!service.checkLiked(pid, uid)) {
                return ResponseEntity.ok("Not liked yet");
            }

            if (service.cancelLike(pid, uid)) {
                return ResponseEntity.ok("Cancel like success");
            } else {
                return ResponseEntity.ok("Cancel like failed");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
