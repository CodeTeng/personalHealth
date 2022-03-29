package com.lt.health.config;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 七牛云配置类
 * @author: 狂小腾
 * @date: 2022/3/29 22:16
 */
@Configuration
public class QiniuConfig {

    @Bean
    public UploadManager uploadManager() {
        com.qiniu.storage.Configuration configuration = new com.qiniu.storage.Configuration(Region.region2());
        return new UploadManager(configuration);
    }
}
