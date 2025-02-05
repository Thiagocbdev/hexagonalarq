package com.transfers.api.rest;

import com.transfers.core.domain.application.service.TransferService;
import com.transfers.core.domain.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    void testGetAllTransfers() {
        // Arrange
        Employee employee1 = new Employee();
        employee1.setFullName("John Doe");
        Employee employee2 = new Employee();
        employee2.setFullName("Jane Doe");
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(transferService.getAllTransfers()).thenReturn(employees);

        // Act
        ResponseEntity<List<Employee>> response = transferController.getAllTransfers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
        verify(transferService, times(1)).getAllTransfers();
    }

    @Test
    void testGetAllTransfersException() {
        // Arrange
        when(transferService.getAllTransfers()).thenThrow(new RuntimeException("Database error"));

        // Act
        ResponseEntity<List<Employee>> response = transferController.getAllTransfers();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(transferService, times(1)).getAllTransfers();
    }
}