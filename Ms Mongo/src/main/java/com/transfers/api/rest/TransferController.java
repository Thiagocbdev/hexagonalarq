package com.transfers.api.rest;

import com.transfers.core.domain.application.service.TransferService;
import com.transfers.core.domain.entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllTransfers() {
        try {
            List<Employee> employees = transferService.getAllTransfers();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.error("[MONGO DB]: Error to get all transfers {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
