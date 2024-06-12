package com.controller.user.follow;

import com.bean.Follow;
import com.controller.base.BaseController;
import com.service.FollowService;
import com.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cc
 * 2024-06-03
 */
@RestController
public class FollowController extends BaseController {
    @Autowired
    private FollowService service;

    @RequestMapping("/checkFollow")
    protected String checkFollow(@RequestParam("Follower") String Follower, @RequestParam("Followee") String Followee) {
        try {
            if (service.checkFollow(Follower, Followee)) {
                return print(successJson(true));
            } else {
                return print(successJson(false));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/Follow")
    protected String follow(@RequestParam("Follower") String Follower, @RequestParam("Followee") String Followee) {
        try {
            if (service.checkFollow(Follower, Followee)) {
                if (service.recoverFollow(Follower, Followee)) {
                    return print(successJson(null));
                }
            }

            String Fid = "Follow-" + RandomUtils.GetRandomNumber(8, 0, 9);

            Follow follow = new Follow(Fid, Follower, Followee, 0);

            if (service.FollowSomeOne(follow)) {
                return print(successJson(null));
            } else {
                return print(errorJson());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/UnFollow")
    public String unFollow(@RequestParam("Follower") String Follower, @RequestParam("Followee") String Followee) {
        try {
            if (!service.checkFollow(Follower, Followee)) {
                return print(successJson("Not follow yet"));
            }

            if (service.cancelFollow(Follower, Followee)) {
                return print(successJson("Cancel follow success"));
            } else {
                return print(errorJson());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/GetFollowee")
    protected String getFollowee(@RequestParam("Followee") String Followee) {
        long count = service.selectFollowee(Followee);

        return print(successJson(count));
    }

    @RequestMapping("/GetFollower")
    protected String getFollower(@RequestParam("Follower") String Follower) {
        long count = service.selectFollower(Follower);

        return print(successJson(count));
    }
}
