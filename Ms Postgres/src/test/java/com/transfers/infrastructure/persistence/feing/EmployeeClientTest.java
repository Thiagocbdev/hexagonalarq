package com.transfers.infrastructure.persistence.feing;

import com.transfers.infrastructure.persistence.feing.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class EmployeeClientTest {

    @MockBean
    private EmployeeClient employeeClient;

    @Test
    void testGetEmployees() {
        // Arrange
        EmployeeDto employee1 = new EmployeeDto(1, "John Doe", "Engineering");
        EmployeeDto employee2 = new EmployeeDto(2, "Jane Smith", "Marketing");
        List<EmployeeDto> expectedEmployees = Arrays.asList(employee1, employee2);

        given(employeeClient.getEmployees()).willReturn(expectedEmployees);

        // Act
        List<EmployeeDto> actualEmployees = employeeClient.getEmployees();

        // Assert
        assertEquals(expectedEmployees, actualEmployees);
    }
}