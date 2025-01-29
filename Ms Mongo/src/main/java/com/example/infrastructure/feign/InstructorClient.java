package com.example.infrastructure.feign;

import com.example.domain.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "person-client", url = "http://localhost:8080/api/persons")
public interface InstructorClient {

    @GetMapping
    List<Person> getAllPersons();
}