package com.atnewline.service;

import com.atnewline.pojo.Type;
import com.atnewline.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86135
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-06-20 20:02:28
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询所有分类信息
     * @return  分类List集合
     */
    Result findAllTypes();
}
