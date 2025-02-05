package com.transfers.api.rest;

import com.transfers.core.domain.application.service.TransferService;
import com.transfers.core.domain.entity.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class TransferControllerTest {

    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferController transferController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransfer_success() {
        Driver driver = new Driver();
        driver.setFullName("John Doe");

        ResponseEntity<String> response = transferController.createTransfer(driver);

        verify(transferService).saveDriver(driver);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Ms-Bff : transfer saved and printed successfully John Doe", response.getBody());
    }

    @Test
    void createTransfer_failure() {
        Driver driver = new Driver();
        driver.setFullName("John Doe");

        doThrow(new RuntimeException("Database error")).when(transferService).saveDriver(driver);

        ResponseEntity<String> response = transferController.createTransfer(driver);

        verify(transferService).saveDriver(driver);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Ms-Bff : transfer saved and printed successfully John Doe", response.getBody());
    }
}