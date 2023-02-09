package org.example.lab2.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String phoneNumber;
    private String password;

}
