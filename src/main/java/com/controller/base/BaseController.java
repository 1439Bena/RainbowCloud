package com.controller.base;

import com.google.gson.GsonBuilder;
import com.vo.PageVo;
import com.vo.ResponseData;
import com.vo.VideoRes;
import com.vo.VideoVo;

/**
 * 底座控制器
 *
 * @author CC
 * @date 2024/05/23
 */
public abstract class BaseController {
    /**
     * 成功响应的数据封装 - 默认
     *
     * @param data
     * @return
     */
    public ResponseData successJson(Object data) {
        return new ResponseData(200, "success", data);
    }

    /**
     * 重载成功响应数据封装方法
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public ResponseData successJson(Integer code, String msg, Object data) {
        return new ResponseData(code, msg, data);
    }

    /**
     * 失败响应的数据封装 - 默认
     *
     * @return
     */
    public ResponseData errorJson() {
        return new ResponseData(500, "error", null);
    }

    /**
     * 重载成功响应数据封装方法
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public ResponseData errorJson(Integer code, String msg, Object data) {
        return new ResponseData(code, msg, data);
    }

    public String print(ResponseData responseData) {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .toJson(responseData);
    }

    public String pring(VideoRes videoRes) {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .toJson(videoRes);
    }

    /**
     * 封装 PageVo 对象
     *
     * @param count
     * @param data
     * @return
     */
    public PageVo pageVo(Long count, Object data) {
        return new PageVo(200, "success", data, count);
    }

    public VideoVo videoVo(String url) {
        return new VideoVo(url);
    }

    public VideoRes successUploadVideo(String url) {
        return new VideoRes(0, videoVo(url));
    }
}