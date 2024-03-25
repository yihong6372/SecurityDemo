package net.geekh.securitydemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("err_log")
public class ErrorLog implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    public Long id;
    @TableField
    public String ip;
    @TableField
    public String url;
    @TableField
    public String method;
    @TableField
    public String content;
    @TableField
    public String logTime;

}
