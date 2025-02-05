package com.transfers.api.event;

import com.transfers.kafka.contracts.DriverDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransferPublisher {

    private final KafkaTemplate<String, DriverDto> kafkaTemplate;

    public void publish(DriverDto driverDto) {
        log.info("KAFKA: PUBLICANDO EVENTO: " + driverDto);
        kafkaTemplate.send("transfer-topic", driverDto);
    }

}
