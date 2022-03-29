package com.lt.health.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 七牛云文件上传工具类
 * @author: 狂小腾
 * @date: 2022/3/29 21:49
 */
@Component
@Slf4j
public class QiniuUtil {
    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Autowired
    private UploadManager uploadManager;

    /**
     * 根据文件字节数据上传文件
     *
     * @param bytes            文件字节数据
     * @param originalFilename 文件名称
     * @return 带有时间戳的文件名
     */
    public String upload(byte[] bytes, String originalFilename) {
        try {
            // 文件上传
            Response response = uploadManager.put(bytes, this.getKey(originalFilename), this.uploadToken());
            // 解析文件上传的结果
            String bodyString = response.bodyString();
            DefaultPutRet putRet = new Gson().fromJson(bodyString, DefaultPutRet.class);
            log.info("文件上传成功==> key:{} <==> hash:{}", putRet.key, putRet.hash);
            return getKey(originalFilename);
        } catch (QiniuException e) {
            Response response = e.response;
            try {
                log.error("文件上传失败==>{}", response.bodyString());
            } catch (QiniuException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据文件路径上传文件
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @return 带有时间戳的文件名
     */
    public String upload(String filePath, String fileName) {
        String name = this.getKey(fileName);
        try {
            Response response = uploadManager.put(filePath, name, this.uploadToken());
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("文件上传成功==> key:{} <==> hash: {}", putRet.key, putRet.hash);
            return name;
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                log.error("文件上传失败==>{}", r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            return null;
        }
    }

    /**
     * 根据文件输入刘上传文件
     *
     * @param stream   文件流
     * @param fileName 文件名称
     * @return 带有时间戳的文件名称
     */
    public String upload(InputStream stream, String fileName) {
        try {
            Response response = uploadManager.put(stream, this.getKey(fileName), this.uploadToken(), null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("文件上传成功==> key:{} <==> hash: {}", putRet.key, putRet.hash);
            return this.getKey(fileName);
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                log.error("文件上传失败==>{}", r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            return null;
        }
    }

    /**
     * 根据文件名生成时间文件名---给文件名称加个时间戳
     *
     * @param originalFilename 文件名称
     * @return 带有时间的文件名称
     */
    private String getKey(String originalFilename) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    /**
     * 鉴权
     *
     * @return 返回鉴权字符串
     */
    private String uploadToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucket);
        if (StringUtils.isBlank(token)) {
            log.info("未获取到token，请重试！！！");
            return null;
        }
        return auth.uploadToken(bucket);
    }
}
