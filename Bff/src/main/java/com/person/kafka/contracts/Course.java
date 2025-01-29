package com.person.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Course {

    @JsonProperty("title")
    private String title;

}
