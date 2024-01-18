package com.bear.service.impl;

import com.bear.entity.SysUser;
import com.bear.enums.UserStatusEnum;
import com.bear.exception.BusinessException;
import com.bear.form.LoginForm;
import com.bear.service.LoginService;
import com.bear.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserServiceImpl userService;

    @Override
    public LoginVO login(LoginForm form) {

        SysUser user = userService.getUserByUserName(form.getUsername());
        checkUserStatus(user);
        // TODO: 2023/11/16 引入redis
        return new LoginVO();
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
