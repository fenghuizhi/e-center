package com.lessly.center.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取accessToken传递的参数
 */
@Data
public class ClientDto implements Serializable {


    /**
     * 将由clientId、clientSecret、sysCode加密生成
     * (1)这三个参数将由 中台 私底下告诉-发给 需要调用服务的调用方
     * (2)加密生成：AES-PKCS5填充模式生成，密钥也是由 中台 私底下告诉-发给 需要调用服务的调用方
     */
    private String encryptStr;

    private String nonceStr;


}
