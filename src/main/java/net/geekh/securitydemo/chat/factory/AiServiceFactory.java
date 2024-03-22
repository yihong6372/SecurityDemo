package net.geekh.securitydemo.chat.factory;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.client.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import net.geekh.securitydemo.chat.config.OpenAiModel;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.Optional;

/**
 * openAiService 工厂
 * <p>* 如无需代理配置请使用 {@link OpenAiService}</p>
 * @author WuHao
 * @since 2023/5/24 10:00
 */
public class AiServiceFactory {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10L);

    public static OpenAiService createService() {

        System.out.println(OpenAiModel.getKeys());
        System.out.println(OpenAiModel.getKeys());
        String token = Optional.ofNullable(OpenAiModel.getKeys()).orElseThrow(() -> new RuntimeException("ApiKey不能为空，请检查参数配置")).stream().findFirst().orElse(null);

        Assert.notEmpty(token,() -> new RuntimeException("ApiKey不能为空，请检查参数配置"));

        ObjectMapper mapper = OpenAiService.defaultObjectMapper();

        // 设置代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(OpenAiModel.getProxyHost(), OpenAiModel.getProxyPort()));

        OkHttpClient client = OpenAiService.defaultClient(token, DEFAULT_TIMEOUT).newBuilder()
                .proxy(proxy)
                .build();
        Retrofit retrofit = OpenAiService.defaultRetrofit(client, mapper);


        OpenAiApi openAiApi = retrofit.create(OpenAiApi.class);

        return new OpenAiService(openAiApi, client.dispatcher().executorService());

    }

}
