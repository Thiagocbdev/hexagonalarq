package com.example.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Data
public class Teacher {

    @Id
    private String id;
    private String name;
    private String subject;
    private Double salary;

}
