package com.bear.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bear.common.Response;
import com.bear.entity.SysUser;
import com.bear.exception.BusinessException;
import com.bear.service.ISysUserService;
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
    public Response<IPage<SysUser>> list(SysUser sysUser) {
        throw new BusinessException("test");
//        System.out.println(sysUser.getCreateTime());
//        IPage<SysUser> page = new Page<>(1,10);
//        IPage<SysUser> page1 = userService.page(page);
//        return Response.success(page1);
    }
}
