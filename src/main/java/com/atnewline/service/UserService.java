package com.atnewline.service;

import com.atnewline.pojo.User;
import com.atnewline.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86135
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-06-20 20:02:28
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param user 用户信息
     * @return  Result
     */
    Result login(User user);

    /**
     * 通过token获取用户信息
     * @param token 请求头传递的token信息
     * @return  用户信息
     */
    Result getUserInfo(String token);

    /**
     * 检查用户名是否被占用
     * @param username
     * @return
     */
    Result checkUserName(String username);

    /**
     * 用户注册
     * @param user 注册信息
     * @return  注册成功/失败
     */
    Result regist(User user);


    /**  判断token是否有效
     *
     */
    Result checkLogin(String token);
}
