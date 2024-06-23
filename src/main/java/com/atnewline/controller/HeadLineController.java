package com.atnewline.controller;

import com.atnewline.pojo.Headline;
import com.atnewline.pojo.vo.HeadLineVo;
import com.atnewline.service.HeadlineService;
import com.atnewline.utils.JwtHelper;
import com.atnewline.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname HeadLineController
 * @Description TODO:头条模块开发
 * @Date 2024/6/21 15:30
 * @Created by xj
 */

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadLineController {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private HeadlineService headlineService;

    //头条发布
    /**
     * 实现步骤:
     *   1. token获取userId [无需校验,拦截器会校验]
     *   2. 封装headline数据
     *   3. 插入数据即可
     */
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline,@RequestHeader String token){

        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        Result result = headlineService.publish(headline);
        return result;
    }

    //头条回显
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid){
        Result result = headlineService.findHeadlineByHid(hid);
        return result;
    }

    //修改头条
    @PostMapping("/update")
    public Result updateArticle(@RequestBody Headline headline){
        Result result = headlineService.updateArticle(headline);
        return result;
    }

    //根据主键删除一条记录(逻辑删除，将is_deleted值改为1)
    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid){
        headlineService.removeById(hid);
        Result result = Result.ok(null);
        return result;
    }
}
