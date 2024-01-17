package com.bear.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bear.rbac.entity.SysUser;
import com.bear.rbac.mapper.SysUserMapper;
import com.bear.rbac.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
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

    public SysUser getUserByUserName(String username) {

        if(StringUtils.isBlank(username)) {
            return null;
        }
        return lambdaQuery().eq(SysUser::getUsername,username).one();
    }
}
