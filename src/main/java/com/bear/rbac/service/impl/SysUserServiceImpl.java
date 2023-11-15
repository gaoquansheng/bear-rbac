package com.bear.rbac.service.impl;

import com.bear.rbac.entity.SysUser;
import com.bear.rbac.mapper.SysUserMapper;
import com.bear.rbac.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author bear
 * @since 2023-11-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
