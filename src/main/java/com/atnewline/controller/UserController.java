package com.atnewline.controller;

import com.atnewline.pojo.User;
import com.atnewline.service.UserService;
import com.atnewline.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2024/6/20 20:29
 * @Created by xj
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *用户登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result = userService.login(user);
        return result;
    }

    //获取用户信息
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result = userService.getUserInfo(token);
        return result;
    }

    //检查用户名是否可用
    @PostMapping("checkUserName")
    public Result checkUserName(String username){
        Result result = userService.checkUserName(username);
        return result;
    }

    //用户注册
    @PostMapping("regist")
    public Result regist(@RequestBody User user){
        Result result = userService.regist(user);
        return result;
    }

    //客户端在进入发布页前、发布新闻前、进入修改页前、修改前、删除新闻前先向服务端发送请求携带token请求头
    //token为空或者过期都不放行
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        Result result= userService.checkLogin(token);
        return result;
    }
}
