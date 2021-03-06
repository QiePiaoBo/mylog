package com.mylog.common.licence.entity;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Dylan
 * @since 2020-05-24
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限说明
     */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
