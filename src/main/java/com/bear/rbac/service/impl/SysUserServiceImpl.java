package com.bear.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.bear.rbac.entity.SysUser;
import com.bear.rbac.mapper.SysUserMapper;
import com.bear.rbac.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
