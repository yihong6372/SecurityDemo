package net.geekh.securitydemo.chat.utils;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import net.geekh.securitydemo.chat.pojo.msg;
import net.geekh.securitydemo.chat.pojo.openAiReq;
import okhttp3.*;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.*;

public class OpenAiProxyExample {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(60L);

    public static void main(String[] args) throws Exception {
        // 设置代理服务器
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));

        // 使用代理创建OkHttpClient实例
        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(DEFAULT_TIMEOUT)
//                .proxy(proxy)
                .build();


        // 定义请求体的媒体类型为JSON
        MediaType jsonType = MediaType.get("application/json; charset=utf-8");

        msg msg = new msg();
        msg.setRole("user");
        msg.setContent("写一个长故事");
        msg msg2 = new msg();
        msg2.setRole("system");
        msg2.setContent("HELLO");

        List<net.geekh.securitydemo.chat.pojo.msg> msgs = new ArrayList<>();
//        msgs.add(msg2);
        msgs.add(msg);


        openAiReq openAiReq = new openAiReq();
        openAiReq.setMessages(msgs);
        openAiReq.setN(1);
        openAiReq.setModel("gpt-3.5-turbo");
        openAiReq.setMax_tokens(1000);


        System.out.println(msg);
        System.out.println(openAiReq);

        String jsonString = JSON.toJSONString(openAiReq);
        System.out.println(jsonString);
        // 构造JSON请求体
//        String json = "{\"prompt\": \"Translate the following English text to French: 'Hello, world'\", \"max_tokens\": 60}";
//        String json ="{\n" +
//                "    \"model\": \"gpt-4-0125-preview\",\n" +
//                "    \"messages\": [\n" +
//                "      {\"role\": \"c\", \"content\": \"Explain asynchronous programming in the style of the pirate Blackbeard.\"} ]\n" +
//                "  }";
        RequestBody body = RequestBody.create("{\n" +
                "    \"model\": \"gpt-4.5-turbo\",\n" +
                "    \"messages\": [\n" +
                "      {\n" +
                "        \"role\": \"user\",\n" +
                "        \"content\": \"什么是java.\"\n" +
                "      }" +
//                ",\n" +
//                "      {\n" +
//                "        \"role\": \"user\",\n" +
//                "        \"content\": \"Hello!\"\n" +
//                "      }\n" +
                "    ]\n" +
                "  }", jsonType);
        MediaType mediaType = body.contentType();
        System.out.println(mediaType);


        // 创建请求
        Request request = new Request.Builder()
                .url("https://burn.hair/v1/chat/completions")
                .addHeader("Authorization", "Bearer sk-h3weNRRwR6RsA1BW7e321117Ee224fAdA0D389A9B95c06D3")
                .post(body)
                // 设置请求体等
                .build();

        // 发送请求
        try (Response response = client.newCall(request).execute()) {
            // 输出响应内容
            System.out.println(response.body().string());
        }
    }

}