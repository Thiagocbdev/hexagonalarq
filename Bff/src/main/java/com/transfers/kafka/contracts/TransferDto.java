package com.transfers.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransferDto {

    @JsonProperty("title")
    private String title;

}
