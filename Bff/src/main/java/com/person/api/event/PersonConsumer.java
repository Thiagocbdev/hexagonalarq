package com.person.api.event;

import com.person.kafka.contracts.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonConsumer {

    @KafkaListener(topics = "person")
    public void listen(Event event) {
        log.info("KAFKA : EVENTO RECEBIDO: MS: " + event);
    }

}
