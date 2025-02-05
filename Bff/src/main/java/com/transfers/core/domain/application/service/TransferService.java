package com.transfers.core.domain.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transfers.api.event.TransferPublisher;
import com.transfers.core.domain.entity.Driver;
import com.transfers.core.domain.port.in.TransferPortin;
import com.transfers.infrastructure.persistence.TransferRepository;
import com.transfers.kafka.contracts.DetailsDto;
import com.transfers.kafka.contracts.DriverDto;
import com.transfers.kafka.contracts.TransferDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TransferService implements TransferPortin {

    private final TransferRepository transferRepository;


    private final TransferPublisher transferPublisher;

    @Override
    public void publishDriver(DriverDto message) {
    }

    public Driver saveDriver(Driver driver) {
        Driver savedDriver = transferRepository.save(driver);
        try {
            log.info("Ms-Bff : Starting parse for: " + driver.getFullName());
            DriverDto driverDto = convertToDto(driver);
            log.info("Ms-Bff : Parse successful: " + driver.getFullName());
            transferPublisher.publish(driverDto);
            log.info("Ms-Bff : Event published: " + driver.getFullName());
        } catch (Exception e){
            log.error("Ms-Bff : Error in parsing: " + e) ;
        }
        return driver;
    }

    public List<Driver> getAllPersons() {
        return transferRepository.findAll();
    }

    private DriverDto convertToDto(Driver driver) {
        DriverDto dto = new DriverDto();
        dto.setFullName(driver.getFullName());

        if (driver.getTransfer() != null) {
            TransferDto transferDto = new TransferDto();
            transferDto.setTitle(driver.getTransfer().getTitle());
            dto.setTransfer(transferDto);
        }

        if (driver.getDetails() != null) {
            DetailsDto detailsDto = new DetailsDto();
            detailsDto.setTotal(driver.getDetails().getTotal());
            detailsDto.setCurrency(driver.getDetails().getCurrency());
            dto.setDetails(detailsDto);
        }

        return dto;
    }



}
