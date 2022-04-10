package com.lt.health.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import com.google.code.kaptcha.Producer;
import com.lt.health.constant.Constants;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 验证码控制器
 * @author: 狂小腾
 * @date: 2022/4/10 16:01
 */
@RestController
@Api(tags = "验证码控制器")
@Slf4j
public class CaptchaController {

    @Autowired
    private RedisUtil redisUtil;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Value("${lt.captchaType}")
    private String captchaType;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    @ApiOperation("生成验证码接口")
    public Result getCode() throws IOException {
        // 1.生成验证码的key
        String uuid = UUID.randomUUID().toString();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 2.生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }
        log.info("验证码为：{}", capStr);
        log.info("结果为：{}", code);
        // 3.存入redis中 2min
        redisUtil.setValueTime(verifyKey, code, Constants.CAPTCHA_EXPIRATION);
        // 4.转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return Result.fail(e.getMessage());
        }

        // 5.返回给前端key和image
        Map<String, Object> map = new HashMap<>(2);
        map.put("uuid", uuid);
        map.put("image", Base64.encode(os.toByteArray()));
        return Result.success(MessageConstant.CAPTCHA_IMAGE_SEND_SUCCESS, map);
    }
}
