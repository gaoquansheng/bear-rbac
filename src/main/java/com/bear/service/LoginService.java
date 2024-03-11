package com.bear.service;

import com.bear.dto.req.LoginForm;
import com.bear.vo.LoginVO;

public interface LoginService {

    LoginVO login(LoginForm form);
}
