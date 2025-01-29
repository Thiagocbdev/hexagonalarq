package com.person.core.domain.port.in;


import com.person.kafka.contracts.Event;

public interface PersonPortin {

    void publishPerson(Event message);

}
