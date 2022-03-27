package com.lt.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.health.entity.Permission;
import com.lt.health.service.PermissionService;
import com.lt.health.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author 狂小腾
* @description 针对表【sys_permission】的数据库操作Service实现
* @createDate 2022-03-26 20:36:16
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService {

}




