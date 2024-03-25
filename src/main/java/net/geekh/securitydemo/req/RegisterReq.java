package net.geekh.securitydemo.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("checkPass")
    private String password2;

    private Integer smsCode;

}
