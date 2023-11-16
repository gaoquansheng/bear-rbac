package com.bear.rbac.controller;

import com.bear.rbac.common.Response;
import com.bear.rbac.form.LoginForm;
import com.bear.rbac.service.LoginService;
import com.bear.rbac.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public Response<LoginVO> login(@RequestBody LoginForm form){

        LoginVO loginVO = loginService.login(form);
        return Response.success(loginVO);
    }

}
