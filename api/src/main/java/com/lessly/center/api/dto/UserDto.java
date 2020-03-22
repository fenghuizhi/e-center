package com.lessly.center.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 */
@Data
public class UserDto implements Serializable {

    private Long userId;

    private String userName;

    private String name;

    private String password;

    private String salt;//盐 加密用

    private String email;

    private String mobile;

    private Integer status;

    private Date createTime;

    private Long deptId;

    private String deptName;

    private String postName;//岗位名称
}
