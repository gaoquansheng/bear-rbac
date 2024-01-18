package com.bear.service.impl;

import com.bear.entity.SysUser;
import com.bear.mapper.SysUserMapper;
import com.bear.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    public SysUser getUserByUserName(String userName) {

        if(StringUtils.isBlank(userName)) {
            return null;
        }
        return lambdaQuery().eq(SysUser::getUserName,userName).one();
    }
}
