package com.atnewline.service.impl;

import com.atnewline.utils.JwtHelper;
import com.atnewline.utils.MD5Util;
import com.atnewline.utils.Result;
import com.atnewline.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atnewline.pojo.User;
import com.atnewline.service.UserService;
import com.atnewline.mapper.UserMapper;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 86135
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-06-20 20:02:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);
        Result result = null;
        if (loginUser == null) {
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
            return result;
        }
        if (StringUtils.isNotBlank(user.getUserPwd()) && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            //用户名密码都正确返回 token(封装到map集合)
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map map = new HashMap<>();
            map.put("token",token);
            result = Result.ok(map);
            return result;
        }
        result = Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        return result;
    }


    //查询用户信息
    @Override
    public Result getUserInfo(String token) {
        Result result = null;
        //1.判断token是否过期，过期直接返回未登录
        if (jwtHelper.isExpiration(token)) {
            result = Result.build(null,ResultCodeEnum.NOTLOGIN);
            return result;
        }
        int userId = jwtHelper.getUserId(token).intValue();
        //根据userId查询用户信息，看看是否能查到，查到则返回
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid,userId);

        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            result = Result.build(null,ResultCodeEnum.NOTLOGIN);
            return result;
        }
        //返回用户信息（将密码致空）
        Map data = new HashMap<>();
        user.setUserPwd("");
        data.put("loginUser",user);
        result = Result.ok(data);
        return result;
    }

    /**
     * 检查用户名是否被占用
     *
     * @param username
     * @return
     */
    @Override
    public Result checkUserName(String username) {
        Result result = null;
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user != null) {
            result = Result.build(null,ResultCodeEnum.USERNAME_USED);
            return result;
        }
        result = Result.ok(null);
        return  result;
    }

    /**
     * 用户注册
     *
     * @param user 注册信息
     * @return 注册成功/失败
     */
    @Override
    public Result regist(User user) {
        Result result = null;
        //1.根据用户名查询是否重名，可能出现抢注册的情况
        User user1 = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (user1 != null){
            result = Result.build(null,ResultCodeEnum.USERNAME_USED);
            return result;
        }
        //2.进行注册(将密码加密再注册)
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int insert = userMapper.insert(user);
        if (insert > 0) {
            result = Result.ok(null);
            return result;
        }
        result = Result.build(null,506,"未知错误");
        return result;
    }

    /**
     * 判断token是否有效
     *
     * @param token
     */
    @Override
    public Result checkLogin(String token) {
        Result result = null;
        if (jwtHelper.isExpiration(token)){
            result = Result.build(null,ResultCodeEnum.NOTLOGIN);
            return result;
        }
        result = Result.ok(null);
        return result;
    }
}




