package com.transfers.infrastructure.persistence.feing;

import com.transfers.infrastructure.persistence.feing.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "employee-service", url = "http://localhost:8081/api")
public interface EmployeeClient {

    @GetMapping("/transfers")
    public List<EmployeeDto> getEmployees();
}

