package com.transfers.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DetailsDto {

    @JsonProperty("total")
    private Double total;

    @JsonProperty("currency")
    private String currency;

}
