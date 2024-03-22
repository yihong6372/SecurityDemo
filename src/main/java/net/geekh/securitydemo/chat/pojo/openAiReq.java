package net.geekh.securitydemo.chat.pojo;

import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.Data;

import java.util.List;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/21 07:00
 */
@Data
public class openAiReq {

    String model;

    List<msg> messages;

    Integer n;


    Integer max_tokens;

    Boolean stream;

}
