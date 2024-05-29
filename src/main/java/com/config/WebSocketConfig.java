package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Cc
 * 2023-12-22
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        //注入ServerEndPointExporter 自动注册使用@ServerEndPoint注解
        return new ServerEndpointExporter();
    }
}
