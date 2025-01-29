package com.person.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.person.core.domain.entity.Course;
import com.person.core.domain.entity.Wage;
import lombok.Data;

@Data
public class Event {

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("course")
    private Course course;

    @JsonProperty("wage")
    private Wage wage;

}
