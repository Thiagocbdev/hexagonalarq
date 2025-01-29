package com.person.core.domain.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.core.domain.entity.Person;
import com.person.core.domain.port.in.PersonPortin;
import com.person.infrastructure.persistence.PersonRepository;
import com.person.kafka.contracts.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements PersonPortin {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void publishPerson(Event message) {
    }

    public Person savePerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return person;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }


}
