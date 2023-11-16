package com.bear.rbac.service;

import com.bear.rbac.form.LoginForm;
import com.bear.rbac.vo.LoginVO;

public interface LoginService {

    LoginVO login(LoginForm form);
}
