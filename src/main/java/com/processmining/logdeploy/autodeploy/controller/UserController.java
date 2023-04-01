package com.processmining.logdeploy.autodeploy.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.processmining.logdeploy.autodeploy.entity.User;
import com.processmining.logdeploy.autodeploy.common.dto.LoginDto;
import com.processmining.logdeploy.autodeploy.common.lang.Result;
import com.processmining.logdeploy.autodeploy.service.UserService;
import com.processmining.logdeploy.autodeploy.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getUserByName(loginDto.getUsername());

        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }

        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(200, "Login Succeed!", MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(200, "Logout Succeed!", null);
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody User user) {
        User temp_user = userService.getUserByName(user.getUsername());

        if (temp_user != null) {
            return Result.fail("该用户名已被使用");
        }

        user.setPassword(SecureUtil.md5(user.getPassword()));
        user.setStatus(User.Status.ACTIVE);
        userService.register(user);
        return Result.success(200, "Register Succeed!", null);
    }

}
