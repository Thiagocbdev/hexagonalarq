package com.transfers.api.event;

import com.transfers.core.domain.application.service.TransferService;
import com.transfers.kafka.contracts.DriverDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransferListenner {

    private final TransferService transferService;

    @KafkaListener(topics = "transfer-topic", groupId = "ms-transfers-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(DriverDto driverDto) {
        log.info("KAFKA: EVENTO RECEBIDO: " + driverDto);

        transferService.saveEmployee(driverDto);
        log.info(" [MONGO DB] transfer salvo com sucesso: {}", driverDto.getFullName());

    }

}
