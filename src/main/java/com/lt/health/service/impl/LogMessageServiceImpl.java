package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.entity.LogMessage;
import com.lt.health.mapper.LogMessageMapper;
import com.lt.health.service.LogMessageService;
import org.springframework.stereotype.Service;

/**
 * @author 腾腾
 * @description 针对表【sys_log_message(日志表)】的数据库操作Service实现
 * @createDate 2022-04-01 23:06:33
 */
@Service
public class LogMessageServiceImpl extends ServiceImpl<LogMessageMapper, LogMessage>
        implements LogMessageService {
}




