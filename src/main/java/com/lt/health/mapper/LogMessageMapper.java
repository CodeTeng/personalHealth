package com.lt.health.mapper;

import com.lt.health.entity.LogMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author 腾腾
 * @description 针对表【sys_log_message(日志表)】的数据库操作Mapper
 * @createDate 2022-04-01 23:06:33
 * @Entity com.lt.health.entity.LogMessage
 */
@Repository
public interface LogMessageMapper extends BaseMapper<LogMessage> {

}




