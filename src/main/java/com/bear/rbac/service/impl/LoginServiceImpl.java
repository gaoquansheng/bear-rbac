package com.bear.rbac.service.impl;

import com.bear.rbac.exception.BusinessException;
import com.bear.rbac.entity.SysUser;
import com.bear.rbac.form.LoginForm;
import com.bear.rbac.service.LoginService;
import com.bear.rbac.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bear.rbac.enums.UserStatusEnum.DISABLE;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserServiceImpl userService;

    @Override
    public LoginVO login(LoginForm form) {

        SysUser user = userService.getUserByUserName(form.getUserName());
        checkUserStatus(user);
        // TODO: 2023/11/16 引入redis
        return null;
    }

    private void checkUserStatus(SysUser user) {

        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        if (StringUtils.equals(DISABLE.getCode(),user.getStatus())) {
            throw new BusinessException("用户已停用，请联系管理员");
        }
    }

}
