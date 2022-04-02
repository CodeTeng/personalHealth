package com.lt.health.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 与微信小程序连同的工具类
 * @author: 狂小腾
 * @date: 2022/4/2 20:51
 */
@Slf4j
public class HttpUtil {
    /**
     * 发送GET请求获取信息
     *
     * @param url url
     */
    public static String getResponse(String url) {
        try {
            // 创建一个client请求
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(url);
            // 发送请求
            CloseableHttpResponse response = client.execute(get);
            log.info("请求响应码：{}", response.getStatusLine().getStatusCode());
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            log.error("GET网络请求发送失败: {}", e.getMessage());
            return "";
        }
    }

    /**
     * 发送POST请求获取信息
     *
     * @param url url
     */
    public static String postResponse(String url, Map<String, String> params) {
        try {
            // 创建一个client请求
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            if (params != null && params.size() > 0) {
                List<NameValuePair> param = new ArrayList<>();
                params.forEach((key, value) -> param.add(new BasicNameValuePair(key, value)));
                post.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));
            }
            // 发送请求
            CloseableHttpResponse response = client.execute(post);
            log.info("请求响应码: {}", response.getStatusLine().getStatusCode());
            String result = EntityUtils.toString(response.getEntity());
            log.info("请求结果: {}", result);
            return result;
        } catch (Exception e) {
            log.error("POST网络请求发送失败: {}", e.getMessage());
            return "";
        }
    }
}
