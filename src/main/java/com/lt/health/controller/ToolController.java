package com.lt.health.controller;

import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.dto.MailDTO;
import com.lt.health.service.ToolService;
import com.lt.health.service.UserService;
import com.lt.health.utils.MD5Util;
import com.lt.health.utils.QiniuUtil;
import com.lt.health.utils.RedisUtil;
import com.lt.health.utils.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Random;

/**
 * @description: 工具控制器
 * @author: 狂小腾
 * @date: 2022/3/27 21:57
 */
@RestController
@RequestMapping("/tool")
@Slf4j
public class ToolController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ToolService toolService;

    @Autowired
    private QiniuUtil qiniuUtil;

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 忘记密码 邮件找回
     *
     * @param mailDTO 邮件参数
     * @return 邮件发送的结果 成功或失败
     */
    @PostMapping("/forget/password")
    public Result forgetPassword(@RequestBody MailDTO mailDTO) {
        if (mailDTO == null) {
            return Result.fail("请填写完整邮件信息！！！");
        }
        String[] receivers = mailDTO.getReceivers();
        if (StringUtils.isAnyBlank(receivers[0])) {
            return Result.fail("请填写收件人邮箱信息！！！");
        }
        mailDTO.setSubject("来自狂小腾的个人运动管理平台的一封短信");
        Random random = new Random();
        // 设置随机密码
        int password = 100000 + random.nextInt(1000000);
        log.info("新密码：" + password);
        // 根据邮箱更新密码
        userService.updatePasswordByMail(mailDTO.getReceivers()[0], passwordEncoder.encode(MD5Util.md5(String.valueOf(password))));
        mailDTO.setContent("你的新密码是：" + password + ".请妥善保管！");
        // 发送邮件
        return toolService.sendMail(mailDTO);
    }

    /**
     * 七牛云图片上传
     */
    @PostMapping("/upload")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String originalFilename = file.getOriginalFilename();
        String url = qiniuUtil.upload(bytes, originalFilename);
        if (StringUtils.isNotEmpty(url)) {
            return Result.success(MessageConstant.PIC_UPLOAD_SUCCESS, url);
        }
        return Result.fail(MessageConstant.PIC_UPLOAD_FAIL);
    }

    /**
     * 发送手机验证码
     *
     * @param phoneNumber 手机号码
     * @return 成功或失败信息
     */
    @PostMapping("/sms")
    public Result sendSms(String phoneNumber) {
        Random random = new Random();
        int code = 100000 + random.nextInt(899999);
        smsUtil.sendSms(phoneNumber, code);
        // 存入redis中 并设置过期时间
        redisUtil.setValueTime(phoneNumber + "sms", code, 5);
        return Result.success(MessageConstant.PHONE_CODE_SEND_SUCCESS);
    }
}
