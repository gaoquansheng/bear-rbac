package com.bear.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bear.common.Response;
import com.bear.entity.SysUser;
import com.bear.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public Response<?> add(@RequestBody SysUser sysUser) {

        return Response.success();
    }

    @PostMapping("/delete")
    public Response<?> delete(String userId) {

        return Response.success();
    }

    @PostMapping("/update")
    public Response<?> update(@RequestBody SysUser sysUser) {

        return Response.success();
    }

    @PostMapping("/get")
    public Response<SysUser> get(String userId) {

        return Response.success();
    }
    @PostMapping("/list")
    public Response<List<SysUser>> list(@RequestBody SysUser sysUser) {

        return null;
    }

    @GetMapping("/page")
    public Response<IPage<SysUser>> page(SysUser sysUser) {

//        IPage<SysUser> page1 = userService.page(sysUser);
        return Response.success();
    }


}
