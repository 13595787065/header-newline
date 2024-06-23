package com.atnewline.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @TableName news_type
 */
@TableName(value ="news_type")
@Data
public class Type implements Serializable {
    @TableId
    private Integer tid;

    private String tname;

    @Version
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}