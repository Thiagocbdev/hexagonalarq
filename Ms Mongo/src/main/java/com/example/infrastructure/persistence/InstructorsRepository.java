package com.example.infrastructure.persistence;

import com.example.domain.entity.Instructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorsRepository extends MongoRepository<Instructor, String> {
}