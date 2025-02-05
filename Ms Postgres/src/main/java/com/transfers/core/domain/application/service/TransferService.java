package com.transfers.core.domain.application.service;

import com.transfers.core.domain.entity.Partner;
import com.transfers.infrastructure.persistence.PartnerRepository;
import com.transfers.infrastructure.persistence.feing.EmployeeClient;
import com.transfers.infrastructure.persistence.feing.dto.EmployeeDto;
import com.transfers.shared.config.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TransferService {

    private final EmployeeClient employeeClient;
    private final EmployeeMapper employeeMapper;
    private final PartnerRepository partnerRepository;

    @Cacheable(value = "employeesCache", key = "#root.method.name")
    @Scheduled(fixedDelay = 20000)
    @Transactional // Add transaction management
    public void getLatestTransfer() {
        List<EmployeeDto> employeeDtos = employeeClient.getEmployees();
        log.info("Fetched {} employees", employeeDtos.size());

        for (EmployeeDto employeeDto : employeeDtos) {
            Partner partner = employeeMapper.employeeDtoToPartner(employeeDto);

            if (partner != null && partner.getPartnerId() != null && !partner.getPartnerId().isEmpty()) {
                Partner existingPartner = partnerRepository.findByPartnerId(partner.getPartnerId());

                if (existingPartner != null) {
                    existingPartner.setFullName(partner.getFullName());
                    existingPartner.setTotal(partner.getTotal());
                    existingPartner.setCurrency(partner.getCurrency());
                    existingPartner.setTitle(partner.getTitle());
                    partnerRepository.save(existingPartner);
                    log.info("Updated partner with partnerId {}", partner.getPartnerId());
                } else {
                    // Save new partner
                    partnerRepository.save(partner);
                    log.info("Saved new partner with partnerId {}", partner.getPartnerId());
                }
            } else {
                String partnerId = (partner != null && partner.getPartnerId() != null) ? partner.getPartnerId() : "null";
                log.warn("Partner with partnerId {} is null or empty, skipping.", partnerId); // Log as warning
            }
        }
    }
}