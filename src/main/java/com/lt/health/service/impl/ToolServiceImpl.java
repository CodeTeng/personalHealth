package com.lt.health.service.impl;

import com.lt.health.constant.Result;
import com.lt.health.entity.dto.MailDTO;
import com.lt.health.service.ToolService;
import com.lt.health.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 具体实现的工具服务类
 * @author: 狂小腾
 * @date: 2022/3/27 22:16
 */
@Service
@Slf4j
public class ToolServiceImpl implements ToolService {

    @Autowired
    private MailUtil mailUtil;

    @Override
    public Result sendMail(MailDTO mailDTO) {
        try {
            String msg = mailUtil.sendMail(mailDTO);
            return Result.success(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("邮件发送失败！");
        }
    }
}
