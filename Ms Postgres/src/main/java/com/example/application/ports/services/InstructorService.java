package com.example.application.ports.services;

import com.example.domain.entity.Instructor;
import com.example.infrastructure.persistence.InstructorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InstructorService {


    private final InstructorRepository instructorRepository;

    @Transactional
    public Instructor saveInstructor(Instructor instructor) {

        if (instructor.getId() != null && instructorRepository.existsById(instructor.getId())) {
            return instructorRepository.save(instructor);
        } else {
            return instructorRepository.save(instructor);
        }
    }
}