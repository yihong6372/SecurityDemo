package net.geekh.securitydemo.chat.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.UnsupportedEncodingException;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/20 18:48
 */
public interface ChatCompletionService {
    boolean send(String content, SseEmitter sseEmitter) throws UnsupportedEncodingException;
}
