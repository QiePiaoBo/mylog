package com.mylog.ds.demo4ys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Dylan
 * @Date : 2021/7/29 - 20:24
 * @Description :
 * @Function :
 */
@Data
@TableName("relation")
public class RelationEntity {

    /**
     * 主键 自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 主体
     */
    private String subject;

    /**
     * 关系 0 关系0, 1 关系1, 2 关系2
     */
    private Integer relationship;

    /**
     * 客体
     */
    private String object;
}
