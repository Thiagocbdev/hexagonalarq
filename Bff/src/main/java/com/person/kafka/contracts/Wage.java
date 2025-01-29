package com.person.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Wage {

    @JsonProperty("total")
    private Double total;

    @JsonProperty("currency")
    private String currency;

}
