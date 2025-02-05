package com.transfers.core.domain.application.service;

import com.transfers.core.domain.entity.Partner;
import com.transfers.infrastructure.persistence.PartnerRepository;
import com.transfers.infrastructure.persistence.feing.EmployeeClient;
import com.transfers.infrastructure.persistence.feing.dto.EmployeeDto;
import com.transfers.shared.config.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TransferServiceTest {

    @Autowired
    private TransferService transferService;

    @MockBean
    private EmployeeClient employeeClient;

    @MockBean
    private EmployeeMapper employeeMapper;

    @MockBean
    private PartnerRepository partnerRepository;

    @Test
    void testGetLatestTransfer() {
        // Arrange
        EmployeeDto employee1 = new EmployeeDto(1, "John Doe", "Engineering");
        EmployeeDto employee2 = new EmployeeDto(2, "Jane Smith", "Marketing");
        List<EmployeeDto> employeeDtos = Arrays.asList(employee1, employee2);

        Partner partner1 = new Partner(1, "John Doe", "Engineering");
        Partner partner2 = new Partner(2, "Jane Smith", "Marketing");
        List<Partner> partners = Arrays.asList(partner1, partner2);

        given(employeeClient.getEmployees()).willReturn(employeeDtos);
        given(employeeMapper.employeeDtoToPartner(employee1)).willReturn(partner1);
        given(employeeMapper.employeeDtoToPartner(employee2)).willReturn(partner2);

        // Act
        transferService.getLatestTransfer();

        // Assert
        ArgumentCaptor<List<Partner>> partnerCaptor = ArgumentCaptor.forClass(List.class);
        verify(partnerRepository).saveAll(partnerCaptor.capture());
        assertEquals(partners, partnerCaptor.getValue());
    }
}