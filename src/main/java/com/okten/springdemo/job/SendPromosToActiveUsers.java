package com.okten.springdemo.job;

import com.google.common.collect.Iterators;
import com.okten.springdemo.repository.WebSocketSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendPromosToActiveUsers {

    private final WebSocketSessionRepository webSocketSessionRepository;
    private final Iterator<String> promos = Iterators.cycle("Спеціальні пропозиції до кінця тижня", "Знижки на колекцію минулого року", "-10% з картою БанкБанк");

    @Scheduled(cron = "0/5 * * * * *")
    public void sendPromos() {
        log.info("Відправляємо спеціальні пропозиції...");
        webSocketSessionRepository.sendToAll(promos.next());
    }

    // delay = 1 hour
    // 1 job - 13:00 - 13:05
    // 2 job - 14:05 - 14:10
    // 3 job - 15:10 - 15:15

    // rate = 1 hour
    // 1 job - 13:00 - 13:05
    // 2 job - 14:00 - 14:05
    // 3 job - 15:00 - 15:05
}
