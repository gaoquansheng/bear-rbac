package com.bear.service.impl;

import com.bear.dto.res.UserDetail;
import com.bear.entity.SysUser;
import com.bear.enums.UserStatusEnum;
import com.bear.exception.BusinessException;
import com.bear.dto.req.LoginForm;
import com.bear.service.LoginService;
import com.bear.utils.RedisUtil;
import com.bear.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserServiceImpl userService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public LoginVO login(LoginForm form) {

        SysUser user = userService.getUserByUserName(form.getUsername());
        checkUserStatus(user);

        UserDetail userDetail = new UserDetail();
        userDetail.setUsername(user.getUsername());
        String token = UUID.randomUUID().toString();
        redisUtil.set(token,userDetail);

        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        return loginVO;
    }

    private void checkUserStatus(SysUser user) {

        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        if (StringUtils.equals(UserStatusEnum.DISABLE.getCode(),user.getStatus())) {
            throw new BusinessException("用户已停用，请联系管理员");
        }
    }

}
