package com.atnewline.service;

import com.atnewline.mapper.HeadlineMapper;
import com.atnewline.pojo.Headline;
import com.atnewline.pojo.vo.HeadLineVo;
import com.atnewline.pojo.vo.PortalVo;
import com.atnewline.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86135
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-06-20 20:02:28
*/
public interface HeadlineService extends IService<Headline> {


    /**
     * 发布文章
     * @param headline
     * @return
     */
    Result publish(Headline headline);

    /**
     * 根据条件返回分页查询的新闻信息
     * @param portalVo 查询条件
     * @return 新闻集合
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     * 根据文章id查询文章详情
     * @param tid   文章id
     * @return  文章详情
     */
    Result showHeadlineDetail(Integer tid);

    /**
     * 根据文章id回显头条信息
     * @param
     * @return
     */
    Result findHeadlineByHid(Integer hid);

    /**
     * 修改头条信息
     * @param
     * @return
     */
    Result updateArticle(Headline headline);
}
