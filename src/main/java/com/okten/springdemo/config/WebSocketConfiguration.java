package com.okten.springdemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import com.okten.springdemo.handler.ReviewHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final ReviewHandler reviewHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(reviewHandler, "/ws/reviews");
    }
}
