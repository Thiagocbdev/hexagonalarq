package com.transfers.infrastructure.persistence;

import com.transfers.core.domain.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {
    boolean existsByPartnerId(String partnerId);
    Partner findByPartnerId(String partnerId);
}