package com.bear.service;

import com.bear.form.LoginForm;
import com.bear.vo.LoginVO;

public interface LoginService {

    LoginVO login(LoginForm form);
}
