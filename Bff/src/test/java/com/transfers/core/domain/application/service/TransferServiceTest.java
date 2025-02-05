package com.transfers.core.domain.application.service;

import com.transfers.api.event.TransferPublisher;
import com.transfers.core.domain.entity.Driver;
import com.transfers.infrastructure.persistence.TransferRepository;
import com.transfers.kafka.contracts.DriverDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private TransferPublisher transferPublisher;

    @InjectMocks
    private TransferService transferService;

    @Captor
    private ArgumentCaptor<Driver> driverCaptor;

    @Captor
    private ArgumentCaptor<DriverDto> driverDtoCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDriver_success() {
        Driver driver = new Driver();
        driver.setFullName("John Doe");

        when(transferRepository.save(driver)).thenReturn(driver);

        Driver savedDriver = transferService.saveDriver(driver);

        verify(transferRepository).save(driverCaptor.capture());
        verify(transferPublisher).publish(driverDtoCaptor.capture());

        assertEquals(driver, driverCaptor.getValue());
        assertNotNull(driverDtoCaptor.getValue());
        assertEquals(driver.getFullName(), driverDtoCaptor.getValue().getFullName());
        assertEquals(driver, savedDriver);
    }

    @Test
    void saveDriver_failure() {
        Driver driver = new Driver();
        driver.setFullName("John Doe");

        when(transferRepository.save(driver)).thenThrow(new RuntimeException("Database error"));

        try {
            transferService.saveDriver(driver);
        } catch (Exception e) {
            assertEquals("Database error", e.getMessage());
        }

        verify(transferRepository).save(driverCaptor.capture());
        verify(transferPublisher, never()).publish(any(DriverDto.class));
    }

    @Test
    void getAllPersons() {
        List<Driver> drivers = List.of(new Driver(), new Driver());
        when(transferRepository.findAll()).thenReturn(drivers);

        List<Driver> result = transferService.getAllPersons();

        verify(transferRepository).findAll();
        assertEquals(drivers, result);
    }
}