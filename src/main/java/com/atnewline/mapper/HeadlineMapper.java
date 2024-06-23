package com.atnewline.mapper;

import com.atnewline.pojo.Headline;
import com.atnewline.pojo.vo.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 86135
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-06-20 20:02:28
* @Entity com.atnewline.pojo.Headline
*/
@Mapper
public interface HeadlineMapper extends BaseMapper<Headline> {

   //自定义分页查询方法
   IPage<Map> selectPageMap(IPage<Map> page, @Param("portalVo") PortalVo portalVo);

   //查看文章详情
   Map showHeadlineDetail(Integer tid);
}

