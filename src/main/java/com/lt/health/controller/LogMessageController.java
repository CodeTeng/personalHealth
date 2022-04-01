package com.lt.health.controller;

import com.lt.health.constant.MessageConstant;
import com.lt.health.constant.Result;
import com.lt.health.entity.LogMessage;
import com.lt.health.service.LogMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 日志控制器
 * @author: 狂小腾
 * @date: 2022/4/1 23:50
 */
@RestController
@RequestMapping("/logMessage")
public class LogMessageController {

    @Autowired
    private LogMessageService logMessageService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<LogMessage> list = logMessageService.list();
        return Result.success(MessageConstant.LOG_MESSAGE_SELECT_SUCCESS, list);
    }
}
