package com.transfers.shared.config.mapper;

import com.transfers.core.domain.entity.Partner;
import com.transfers.infrastructure.persistence.feing.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "partnerId", source = "id")
    @Mapping(target = "title", source = "serviceTitle.title")  // Map from serviceTitle.title
    @Mapping(target = "total", source = "price.total")        // Map from price.total
    @Mapping(target = "currency", source = "price.currency")      // Map from price.currency
    Partner employeeDtoToPartner(EmployeeDto dto);


}