package com.bear.rbac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bear.rbac.common.Response;
import com.bear.rbac.entity.SysUser;
import com.bear.rbac.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author bear
 * @since 2023-11-15
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @GetMapping("/list")
    public Response list() {
        IPage<SysUser> page = new Page<>(1,10);
        IPage<SysUser> page1 = userService.page(page);
        return Response.success(page1);
    }
}
