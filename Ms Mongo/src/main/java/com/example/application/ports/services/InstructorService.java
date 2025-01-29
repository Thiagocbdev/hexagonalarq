package com.example.application.ports.services;

import com.example.application.ports.in.GetInstructorsUseCase;
import com.example.domain.entity.Instructor;
import com.example.domain.entity.Person;
import com.example.infrastructure.feign.InstructorClient;
import com.example.infrastructure.persistence.InstructorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InstructorService implements GetInstructorsUseCase {

    @Autowired
    private InstructorClient instructorClient;

    @Autowired
    private InstructorsRepository instructorsRepository;

    @Autowired
    private ModelMapper mapper;

    @Scheduled(fixedRate = 10000)
    public void fetchAndSaveInstructors() {
        log.info("InstructorService: Starting scheduled task to fetch all persons.");
        try {

            List<Person> persons = instructorClient.getAllPersons();
            log.info("InstructorService: Successfully fetched {} persons.", persons.size());


            List<Instructor> newInstructors = persons.stream()
                    .filter(person -> !instructorsRepository.existsById(person.getId()))
                    .map(person -> mapper.map(person, Instructor.class))
                    .collect(Collectors.toList());

            log.info("InstructorService: Found {} new instructors to save.", newInstructors.size());


            if (!newInstructors.isEmpty()) {
                instructorsRepository.saveAll(newInstructors);
                log.info("InstructorService: Successfully saved {} new instructors to the database.", newInstructors.size());
            } else {
                log.info("InstructorService: No new instructors to save.");
            }
        } catch (Exception e) {
            log.error("InstructorService: Error occurred during scheduled task: {}", e.getMessage(), e);
        }
    }

    public Instructor saveInstructor(Instructor instructor) {
        log.info("InstructorService: Attempting to save instructor with ID: {}", instructor.getId());
        if (instructorsRepository.existsById(instructor.getId())) {
            log.warn("InstructorService: Instructor with ID: {} already exists. Skipping save.", instructor.getId());
            return instructor;
        }
        try {
            Instructor savedInstructor = instructorsRepository.save(instructor);
            log.info("InstructorService: Successfully saved instructor: {}", instructor.getFullName());
            return savedInstructor;
        } catch (Exception e) {
            log.error("InstructorService: Error saving instructor: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<Instructor> getAllInstructors() {
        log.info("InstructorService: Fetching all instructors from the database.");
        try {
            List<Instructor> instructors = instructorsRepository.findAll();
            log.info("InstructorService: Successfully fetched {} instructors.", instructors.size());
            return instructors;
        } catch (Exception e) {
            log.error("InstructorService: Error fetching instructors: {}", e.getMessage(), e);
            throw e;
        }
    }

}
