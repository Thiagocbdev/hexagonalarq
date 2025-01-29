package com.person.api.rest;

import com.person.api.event.PersonConsumer;
import com.person.core.domain.application.service.PersonService;
import com.person.core.domain.entity.Person;
import com.person.kafka.contracts.Event;
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
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    private PersonConsumer personConsumer;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        log.info("Ms-Bff : Person received from frontend: " + person.getFullName());
        log.info("Ms-Bff : [MONGO DB]: Starting persistence in database for: " + person.getFullName());
        try {
            personService.savePerson(person);
        } catch (Exception e) {
            log.error("Ms-Bff : [MONGO DB]: Error saving person {}", String.valueOf(e));
        }
        try {
            log.info("Ms-Bff : Starting parse for: " + person.getFullName());
            Event event = mapper.map(person, Event.class);
            log.info("Ms-Bff : Parse successful: " + person.getFullName());
            personConsumer.listen(event);
            log.info("Ms-Bff : Event published: " + person.getFullName());
        } catch (Exception e){
            log.error("Ms-Bff : Error in parsing: " + e) ;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Ms-Bff : Person saved and printed successfully" + person.getFullName());
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

}
