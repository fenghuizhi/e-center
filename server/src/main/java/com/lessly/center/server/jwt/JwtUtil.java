package com.lessly.center.server.jwt;
import com.debug.center.common.response.BaseResponse;
import com.debug.center.common.response.StatusCode;
import com.debug.center.common.utils.Constant;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.DateTime;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * jwt通用工具类
 * @Author:debug (SteadyJack)
 * @Date: 2019/12/10 21:51
 **/
public class JwtUtil {

    //生成密钥
    public static SecretKey generalKey(){
        byte[] encodedKey= Base64.decode(Constant.JWT_SECRET);
        return new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
    }

    //创建token
    public static String createJWT(final String id,final String subject,final Long expireMills){
        //定义生成签名的算法
        SignatureAlgorithm algorithm= SignatureAlgorithm.HS256;
        //定义生成签名的密钥
        SecretKey key=generalKey();

        Date now= DateTime.now().toDate();
        //借助于第三方依赖组件jwt的api来实现
        JwtBuilder builder= Jwts.builder()
                //用户id
                .setId(id)
                //主体
                .setSubject(subject)
                //签发者
                .setIssuer(Constant.TOKEN_ISSUER)
                //签发时间
                .setIssuedAt(now)
                //签发时指定 加密算法、密钥
                .signWith(algorithm,key);

        //设定过期时间
        if (expireMills>=0){
            Long realExpire=System.currentTimeMillis() + expireMills;
            builder.setExpiration(new Date(realExpire));
        }

        //生成access token
        return builder.compact();
    }

    //验证解析token
    public static BaseResponse<Claims> validateJWT(final String accessToken){
        BaseResponse response=new BaseResponse<>(StatusCode.Success);
        Claims claims;
        try {
            claims=parseJWT(accessToken);
            response.setData(claims);
        }catch (ExpiredJwtException e){
            response=new BaseResponse(StatusCode.TokenValidateExpireToken);
        }catch (SignatureException e){
            response=new BaseResponse(StatusCode.TokenValidateCheckFail);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.TokenValidateCheckFail);
        }
        return response;
    }


    //解析token
    public static Claims parseJWT(final String accessToken) throws Exception{
        SecretKey key=generalKey();
        return Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody();
    }

//    public static void main(String[] args) {
//        String token=createJWT("vxuwzav2cu","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ2eHV3emF2MmN1Iiwic3ViIjoiVUNUWXc3YS81S25VdHI3Z0k1LytUMUtSNkZ1TkF0STlPUWhFZG1NZkxPUWRoK2Nxam5sSDF5M0l1QVAvZUVVMTFab0gyejJGaGFndnNIMW5rdFpMbXlGOWFKNUwrc2k3bVNyclYzOERaOEVsOWI2b0Y5NVRiWXRGcVM3L0IxbHoiLCJpc3MiOiLnqIvluo_lkZjlrp7miJjln7rlnLAiLCJpYXQiOjE1NzU1NDEzNTEsImV4cCI6MTU3NTU0NDk1MX0.lhRPQURElLOL4BA0i2XZD6-FKmwKTTeYATkFuf3Ihv4",10000L);
//        System.out.println(token);
//        System.out.println(validateJWT(token).getData());
//    }
}










































