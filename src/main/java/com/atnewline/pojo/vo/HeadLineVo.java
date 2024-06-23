package com.atnewline.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname HeadLineVo
 * @Description TODO
 * @Date 2024/6/21 16:26
 * @Created by xj
 */
@Data
public class HeadLineVo implements Serializable {
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
}
