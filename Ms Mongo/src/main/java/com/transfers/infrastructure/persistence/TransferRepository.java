package com.transfers.infrastructure.persistence;

import com.transfers.core.domain.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends MongoRepository<Employee, String> {
}