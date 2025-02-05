package com.transfers.api.rest;

import com.transfers.core.domain.application.service.TransferService;
import com.transfers.core.domain.entity.Driver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<String> createTransfer(@RequestBody Driver driver) {
        log.info("Ms-Bff : transfer received from frontend: " + driver.getFullName());
        log.info("Ms-Bff : [MONGO DB]: Starting persistence in database for: " + driver.getFullName());
        try {
            transferService.saveDriver(driver);
        } catch (Exception e) {
            log.error("Ms-Bff : [MONGO DB]: Error saving transfer {}", String.valueOf(e));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Ms-Bff : transfer saved and printed successfully " + driver.getFullName());
    }

}
