package com.example.domain.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "instructors")
public class Instructor {
    @Id
    private String id;
    private String fullName;
    private Course course;
    private Wage wage;

}
