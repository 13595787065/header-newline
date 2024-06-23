package com.atnewline.controller;

import com.atnewline.pojo.vo.PortalVo;
import com.atnewline.service.HeadlineService;
import com.atnewline.service.TypeService;
import com.atnewline.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname ProtalController
 * @Description TODO:新闻首页模块
 * @Date 2024/6/21 10:43
 * @Created by xj
 */
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private HeadlineService headlineService;
    @Autowired
    private TypeService typeService;
    //查询所有分类信息
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid) {
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
