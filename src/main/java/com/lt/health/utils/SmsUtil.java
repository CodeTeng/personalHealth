package com.lt.health.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: 阿里云发送短信服务工具类
 * @author: 狂小腾
 * @date: 2022/3/31 14:20
 */
@Component
@Slf4j
public class SmsUtil {
    @Value("${aliyun.accessKey}")
    private String accessKey;

    @Value("${aliyun.secretKey}")
    private String secretKey;

    @Value("${aliyun.signName}")
    private String signName;

    @Value("${aliyun.templateCode}")
    private String templateCode;

    /**
     * 构建发送短信的连接
     *
     * @return 短信的连接
     * @throws Exception exception
     */
    public Client createClient() throws Exception {
        Config config = new Config();
        config.setAccessKeyId(accessKey)
                .setAccessKeySecret(secretKey);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

    /**
     * 发送短信
     *
     * @param phoneNumber 手机号码
     * @param code        验证码
     */
    public void sendSms(String phoneNumber, int code) {
        try {
            Client client = this.createClient();
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phoneNumber)
                    .setSignName(signName)
                    .setTemplateCode(templateCode)
                    .setTemplateParam("{ code: " + code + " }");
            SendSmsResponse response = client.sendSms(request);
            log.info("短信发送结果--> {}", response.getBody().code + "----------" + response.getBody().message);
        } catch (Exception e) {
            log.error("短信发送失败--> {}", e.getMessage());
        }
    }
}
