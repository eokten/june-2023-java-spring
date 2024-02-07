package com.okten.springdemo.service;

import com.okten.springdemo.entity.Review;
import com.okten.springdemo.repository.ReviewRepository;
import com.okten.springdemo.repository.WebSocketSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final WebSocketSessionRepository webSocketSessionRepository;

    public void saveReview(Long productId, String text) {
        Review review = new Review();
        review.setProductId(productId);
        review.setText(text);
        reviewRepository.save(review);
        webSocketSessionRepository.sendToAll("Новий відгук: %s".formatted(text));
    }
}
