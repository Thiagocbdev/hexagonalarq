package com.transfers.api.event;

import com.transfers.kafka.contracts.DriverDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class TransferPublisherTest {

    @Mock
    private KafkaTemplate<String, DriverDto> kafkaTemplate;

    @InjectMocks
    private TransferPublisher transferPublisher;

    @Captor
    private ArgumentCaptor<DriverDto> driverDtoCaptor;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPublish() {
        DriverDto driverDto = new DriverDto();
        transferPublisher.publish(driverDto);

        verify(kafkaTemplate).send(stringCaptor.capture(), driverDtoCaptor.capture());

        assertEquals("transfer-topic", stringCaptor.getValue());
        assertEquals(driverDto, driverDtoCaptor.getValue());
    }
}