package com.lessly.center.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录需要提供的参数
 */
@Data
public class LoginDto implements Serializable {

    private String userName;

    private String password;
}
