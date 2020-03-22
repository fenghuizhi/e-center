package com.lessly.center.server.jwt;

import com.debug.center.api.dto.ClientDto;
import com.debug.center.common.response.BaseResponse;
import com.debug.center.common.response.StatusCode;
import com.debug.center.common.utils.EncryptUtil;
import com.debug.center.model.entity.ClientConfig;
import com.debug.center.model.entity.ClientToken;
import com.debug.center.model.mapper.ClientConfigMapper;
import com.debug.center.model.mapper.ClientTokenMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * jwt创建access token服务
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2019/12/5 17:59
 **/
@Service
public class JwtTokenService {

    private static final Logger log= LoggerFactory.getLogger(JwtTokenService.class);

    @Autowired
    private Environment env;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientConfigMapper clientConfigMapper;

    @Autowired
    private ClientTokenMapper clientTokenMapper;



    /**
     * 创建AccessToken
     * @param dto
     * @return
     * @throws Exception
     */
    public String createAuthToken(final ClientDto dto) throws Exception{
        if (StringUtils.isNotBlank(dto.getEncryptStr())){
            //TODO:对调用者传递过来的加密串进行解密-判断clientId/clientSecret的合法性
            String decodeStr=EncryptUtil.aesDecrypt(dto.getEncryptStr(),env.getProperty("service.rest.invoke.auth.key"));
            ClientConfig config=objectMapper.readValue(decodeStr, ClientConfig.class);

            ClientConfig entity=clientConfigMapper.selectByIdSecret(config.getClientId(),config.getClientSecret());
            if (entity==null){
                throw new RuntimeException(StatusCode.CurrClientIdSecretNotExist.getMsg());
            }

            //TODO:调用jwt工具类，创建AccessToken
            String accessToken=JwtUtil.createJWT(entity.getClientId(),dto.getEncryptStr(),env.getProperty("service.rest.invoke.token.ttl",Long.class));

            saveAccessToken(config,accessToken);
            return accessToken;
        }

        return null;
    }

    //TODO:保存生成的accessToken
    private void saveAccessToken(final ClientConfig config,final String accessToken){
        ClientToken token=new ClientToken();
        token.setClientId(config.getClientId());
        token.setAccessToken(accessToken);
        token.setCreateTime(new Date());
        clientTokenMapper.insertSelective(token);
    }


    //TODO:jwt验证解析access token
    public BaseResponse validateToken(final String accessToken){

        //TODO:校验access token的合法性来源
        ClientToken token=clientTokenMapper.selectByToken(accessToken);
        if (token==null){
            return new BaseResponse(StatusCode.AuthAccessTokenNotExist);
        }

        //TODO:调用jwt，执行access token认证的核心逻辑
        BaseResponse<Claims> response=JwtUtil.validateJWT(accessToken);
        if (!Objects.equals(response.getCode(),StatusCode.Success.getCode())){
            return response;
        }

        //Claims claimsresponse.getData();

        return response;
    }


}
























