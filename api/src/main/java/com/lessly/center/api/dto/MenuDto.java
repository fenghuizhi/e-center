package com.lessly.center.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 系统菜单信息
 */
@Data
public class MenuDto implements Serializable {

    private Long menuId;

    private String name;

    private Long parentId;

    private String parentName;

    private String url;

    //权限
    private String perms;

    //类型 0：目录 1：菜单 2：按钮
    private Integer type;

    private String icon;

    private Integer orderNum;

    //所属系统编码
    private String sysCode;

    private String sysName;

    private Boolean open;

    private List<?> list;
}
