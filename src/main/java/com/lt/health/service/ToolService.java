package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.dto.MailDTO;

/**
 * @description: 工具控制器的服务接口
 * @author: 狂小腾
 * @date: 2022/3/27 22:15
 */
public interface ToolService {
    /**
     * 发送邮件
     *
     * @param mailDTO 邮件参数
     * @return 邮件发送的结果 成功或失败
     */
    Result sendMail(MailDTO mailDTO);
}
