package com.transfers.core.domain.application.service;

import com.transfers.core.domain.entity.Employee;
import com.transfers.core.domain.entity.Price;
import com.transfers.core.domain.entity.ServiceTitle;
import com.transfers.core.domain.port.in.TransferPortin;
import com.transfers.infrastructure.persistence.TransferRepository;
import com.transfers.kafka.contracts.DriverDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TransferService implements TransferPortin {

    private final TransferRepository transferRepository;
    private final ModelMapper modelMapper;

    @Override
    public void publishDriver(DriverDto message) {
    }

    public Employee saveEmployee(DriverDto driverDto) {

        Employee employee = modelMapper.map(driverDto, Employee.class);

        if (driverDto.getDetails() != null) {
            Price price = new Price();
            price.setTotal(driverDto.getDetails().getTotal());
            price.setCurrency(driverDto.getDetails().getCurrency());
            employee.setPrice(price);
        }

        if (driverDto.getTransfer() != null) {
            ServiceTitle service = new ServiceTitle();
            service.setTitle(driverDto.getTransfer().getTitle());
            employee.setServiceTitle(service);
        }

        Employee savedEmployee = transferRepository.save(employee);
        return savedEmployee;
    }

    public List<Employee> getAllTransfers() {
        return transferRepository.findAll();
    }

}
