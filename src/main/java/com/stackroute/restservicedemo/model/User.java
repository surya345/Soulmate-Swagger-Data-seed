package com.stackroute.restservicedemo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Document(collection = "users")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;


}
