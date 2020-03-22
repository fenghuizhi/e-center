package com.lessly.center.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码实体
 */
@Data
public class UpdatePsdDto implements Serializable {

    private String userName;

    private String oldPsd;

    private String newPsd;
}
