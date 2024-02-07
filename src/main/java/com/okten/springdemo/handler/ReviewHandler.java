package com.okten.springdemo.handler;

import com.okten.springdemo.repository.WebSocketSessionRepository;
import com.okten.springdemo.service.ReviewService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewHandler extends TextWebSocketHandler {

    private final ReviewService reviewService;

    private final WebSocketSessionRepository webSocketSessionRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Нове з'єднання");
        webSocketSessionRepository.addSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String reviewText = message.getPayload();
        log.info("Отримано новий відгук: {}", reviewText);
        reviewService.saveReview(1L, reviewText);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("З'єднання втрачено");
        webSocketSessionRepository.removeSession(session);
    }
}
