package com.atnewline.service.impl;

import com.atnewline.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atnewline.pojo.Type;
import com.atnewline.service.TypeService;
import com.atnewline.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 86135
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-06-20 20:02:28
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{
    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询所有分类信息
     *
     * @return 分类List集合
     */
    @Override
    public Result findAllTypes() {
        Result result = null;
        List<Type> types = typeMapper.selectList(new LambdaQueryWrapper<>());
        result = Result.ok(types);
        return result;
    }
}




