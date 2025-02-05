package com.transfers.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DriverDto {

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("transfer")
    private TransferDto transfer;

    @JsonProperty("details")
    private DetailsDto details;

}
