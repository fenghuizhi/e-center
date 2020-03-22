package com.lessly.center.api.response;

/**
 * 响应状态码及错误码枚举
 */
public enum  CenterStatus {


    Success(0,"成功"),
    Fail(-1,"失败"),
    InvalidParams(201,"非法的参数!"),

    PasswordCanNotBlank(1000,"密码不能为空!"),
    OldPasswordNotMatch(1001,"原密码不正确!"),
    UpdatePasswordFail(1002,"修改密码失败~请联系管理员!"),

    AuthTokenCreateFail(2001,"access token创建失败！"),

    UserAccountNotExist(4001,"当前用户账号-用户名不存在!"),
    UserAccountHasBeenDisabled(4002,"当前用户账号已被禁用,请联系管理员!"),
    UserNamePasswordNotMatch(4003,"当前用户名、密码不匹配，请检查！"),

    LoginFail(4004,"登录失败，请联系管理员！"),

    SysConfigCodeNotExist(5001,"当前系统编码不存在，请进行配置！"),

    ;


    private Integer code;
    private String msg;

    CenterStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
