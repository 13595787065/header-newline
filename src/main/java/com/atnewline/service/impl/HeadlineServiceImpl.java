package com.atnewline.service.impl;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.atnewline.pojo.vo.HeadLineVo;
import com.atnewline.pojo.vo.PortalVo;
import com.atnewline.utils.JwtHelper;
import com.atnewline.utils.Result;
import com.atnewline.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atnewline.pojo.Headline;
import com.atnewline.service.HeadlineService;
import com.atnewline.mapper.HeadlineMapper;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 86135
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-06-20 20:02:28
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 发布文章信息
     * @param headline 文章标题、内容、类别
     * @return
     */
    @Override
    public Result publish(Headline headline) {
        Result result = null;
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);
        result = Result.ok(null);
        return result;
    }
    /**
     * 根据条件返回分页查询的新闻信息
     *
     * @param portalVo
     * @return
     */
    @Override
    public Result findNewsPage(PortalVo portalVo) {
        //2.分页参数
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());

        //3.分页查询
        headlineMapper.selectPageMap(page, portalVo);

        //4.结果封装
        //分页数据封装
        Map<String,Object> pageInfo =new HashMap<>();
        pageInfo.put("pageData",page.getRecords());
        pageInfo.put("pageNum",page.getCurrent());
        pageInfo.put("pageSize",page.getSize());
        pageInfo.put("totalPage",page.getPages());
        pageInfo.put("totalSize",page.getTotal());

        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("pageInfo",pageInfo);
        // 响应JSON
        return Result.ok(pageInfoMap);
    }

    /**
     * 根据文章id查询文章详情
     *
     * @param hid 文章id
     * @return 文章详情
     */
    @Override
    public Result showHeadlineDetail(Integer hid) {
        Result result = null;
        Map headline = headlineMapper.showHeadlineDetail(hid);
        Map map = new HashMap<>();
        map.put("headline",headline);

        //修改文章，使浏览量+1
        Headline headline1 = new Headline();

        headline1.setHid(hid);
        headline1.setVersion((Integer) headline.get("version"));
        headline1.setPageViews((Integer) headline.get("pageViews")+1);
        headlineMapper.updateById(headline1);

        result = Result.ok(map);
        return result;
    }

    /**
     * 根据文章id回显头条信息
     *
     * @param hid
     * @return
     */
    @Override
    public Result findHeadlineByHid(Integer hid) {
        Result result = null;
        Headline headline = headlineMapper.selectById(hid);
        Map map = new HashMap();
        map.put("headline",headline);
        result = Result.ok(map);
        return result;
    }

    /**
     * 修改头条信息
     *
     * @param headline 要修改的信息
     * @return
     */
    @Override
    public Result updateArticle(Headline headline) {
        //读取版本
        Integer version = headlineMapper.selectOne(new LambdaQueryWrapper<Headline>().eq(Headline::getHid, headline.getHid())).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        int update = headlineMapper.update(headline, new LambdaQueryWrapper<Headline>().eq(Headline::getHid, headline.getHid()));
        return Result.ok(null);
    }
}




