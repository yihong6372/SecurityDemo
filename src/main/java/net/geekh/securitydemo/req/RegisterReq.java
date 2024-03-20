package net.geekh.securitydemo.req;

import lombok.Data;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/17 12:49
 */
@Data
public class RegisterReq {

    private String email;

    private String userName;

    private String password;

    private String password2;

    private Integer smsCode;

}
