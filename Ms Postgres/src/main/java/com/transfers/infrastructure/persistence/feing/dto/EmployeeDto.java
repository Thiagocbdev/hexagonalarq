package com.transfers.infrastructure.persistence.feing.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {

    private String id;
    private String fullName;
    private ServiceTitleDto serviceTitle;
    private PriceDto price;
}
