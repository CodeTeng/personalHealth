package com.lt.health.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.User;
import com.lt.health.entity.WxRun;
import com.lt.health.entity.dto.Encrypted;
import com.lt.health.service.UserService;
import com.lt.health.service.WxRunService;
import com.lt.health.utils.DateUtil;
import com.lt.health.utils.DecryptDataUtil;
import com.lt.health.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 小程序控制器
 * @author: 狂小腾
 * @date: 2022/4/2 20:41
 */
@RestController
@RequestMapping("/mini")
@Slf4j
public class MiniController {

    @Value("${mini.appid}")
    private String appid;

    @Value("${mini.secret}")
    private String secret;

    @Autowired
    private UserService userService;

    @Autowired
    private WxRunService wxRunService;

    /**
     * 小程序登录接口
     *
     * @param code WX客户端发送的code
     * @return session_key openId
     */
    @GetMapping("/login")
    public Result login(String code) {
        if (StringUtils.isBlank(code)) {
            return Result.fail(MessageConstant.LOGIN_FAIL);
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" +
                appid +
                "&secret=" +
                secret +
                "&js_code=" +
                code +
                "&grant_type=authorization_code";
        // 发送请求
        String response = HttpUtil.getResponse(url);
        if (StringUtils.isBlank(response)) {
            return Result.fail(MessageConstant.LOGIN_FAIL);
        }
        // 封装返回值
        JSONObject jsonObject = JSON.parseObject(response);
        String openid = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        log.info("微信小程序唯一标识：{}", openid);
        return userService.miniLogin(openid, sessionKey);
    }

    /**
     * 根据openid更新用户信息
     *
     * @param user 用户信息
     * @return 成功或失败信息
     */
    @PostMapping("/update/info")
    public Result updateInfo(@RequestBody User user) {
        return userService.updateInfoByOpenid(user);
    }

    /**
     * 获取WX运动步数接口
     *
     * @param encrypted WX小程序加密数据
     * @return WX步数和提示信息
     */
    @PostMapping("/wxrun")
    public Result getRunStep(@RequestBody Encrypted encrypted) {
        String encryptedData = encrypted.getEncryptedData();
        String openid = encrypted.getOpenid();
        String sessionKey = encrypted.getSessionKey();
        String iv = encrypted.getIv();
        if (StringUtils.isAnyBlank(encryptedData, sessionKey, iv, openid)) {
            return Result.fail(MessageConstant.WX_STEP_FAILURE);
        }
        String data = DecryptDataUtil.decryptData(encryptedData, sessionKey, iv);
        log.info("加密数据-->{}", data);
        JSONObject jsonObject = JSON.parseObject(data);
        JSONArray stepInfoList = jsonObject.getJSONArray("stepInfoList");
        // 实时获取的WX步数集合
        List<WxRun> runs = new ArrayList<>();
        for (Object o : stepInfoList) {
            // 每个对象包括时间戳和运动步数
            JSONObject obj = (JSONObject) o;
            // 时间戳 标识对应的时间
            Long timestamp = obj.getLong("timestamp");
            // 运动步数
            Integer step = obj.getInteger("step");
            String realTime = DateUtil.timeStampConvertString(timestamp);
            WxRun wxrun = new WxRun(openid, realTime, step);
            runs.add(wxrun);
        }
        return wxRunService.getRunStep(runs);
    }

    /**
     * WX步数记录接口
     *
     * @return WX步数记录
     */
    @GetMapping("/step/report")
    public Result stepReport() {
        return wxRunService.stepReport();
    }
}
