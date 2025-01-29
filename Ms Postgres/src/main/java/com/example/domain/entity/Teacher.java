package com.example.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Teacher {
    private int id;
    private String name;
    private String subject;
    private Double salary;

}
