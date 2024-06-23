package com.atnewline.mapper;

import com.atnewline.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86135
* @description 针对表【news_user】的数据库操作Mapper
* @createDate 2024-06-20 20:02:28
* @Entity com.atnewline.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




