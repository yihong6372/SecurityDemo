package net.geekh.securitydemo.chat.service.impl;

import cn.hutool.core.text.StrPool;
import cn.hutool.json.JSONUtil;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import net.geekh.securitydemo.chat.factory.AiServiceFactory;
import net.geekh.securitydemo.chat.service.ChatCompletionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/20 18:49
 */
@Service
@Slf4j
public class ChatCompletionServiceImpl implements ChatCompletionService {

    @Override
    public boolean send(String content, SseEmitter sseEmitter) throws UnsupportedEncodingException {
        OpenAiService service = AiServiceFactory.createService();

        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), URLDecoder.decode(content, "UTF-8"));

        messages.add(systemMessage);
        System.out.println(messages);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(2000)
                .logitBias(new HashMap<>())
                .build();

        service.streamChatCompletion(chatCompletionRequest)
                .doOnError(Throwable::printStackTrace)
                .blockingForEach(chunk -> {
                    log.info(JSONUtil.toJsonStr(chunk));
                    List<ChatCompletionChoice> choices = chunk.getChoices();
                    String sendContent = Optional.ofNullable(choices)
                            .orElseGet(ArrayList::new)
                            .stream()
                            .map(item -> Optional.ofNullable(
                                    Optional.ofNullable(item.getMessage()).orElseGet(ChatMessage::new).getContent()
                            ).orElse(StrPool.LF))
                            .collect(Collectors.joining());
                    sseEmitter.send(sendContent, MediaType.APPLICATION_JSON);
//                    log.info("sendContent>{} ",sendContent);
                });

        service.shutdownExecutor();
        return true;
    }
}
