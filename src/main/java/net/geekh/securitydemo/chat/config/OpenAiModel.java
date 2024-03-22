package net.geekh.securitydemo.chat.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/20 14:35
 */
@ConfigurationProperties(prefix = "openai")
@Configuration
public class OpenAiModel {

    private static String proxyHost;

    private static Integer proxyPort;

    private static List<String> keys;

    public static String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        OpenAiModel.proxyHost = proxyHost;
    }

    public static Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        OpenAiModel.proxyPort = proxyPort;
    }


    public static List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        OpenAiModel.keys = keys;
    }
}


