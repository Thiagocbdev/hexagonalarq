package com.transfers.api.event;

import com.transfers.core.domain.application.service.TransferService;
import com.transfers.kafka.contracts.DriverDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransferListennerTest {

    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferListenner transferListenner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListen() {
        // Arrange
        DriverDto driverDto = new DriverDto();
        driverDto.setFullName("John Doe");

        // Act
        transferListenner.listen(driverDto);

        // Assert
        ArgumentCaptor<DriverDto> driverDtoCaptor = ArgumentCaptor.forClass(DriverDto.class);
        verify(transferService, times(1)).saveEmployee(driverDtoCaptor.capture());
        assertEquals("John Doe", driverDtoCaptor.getValue().getFullName());
    }
}