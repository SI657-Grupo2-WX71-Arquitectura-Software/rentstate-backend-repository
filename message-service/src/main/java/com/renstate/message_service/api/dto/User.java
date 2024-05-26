package com.renstate.message_service.api.dto;



import jakarta.persistence.*;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="users")
public class User {


    private Long id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String description = "Here you can write a description about yourself.";
    private String email;
    private String username;
    private String password;
    private String gender;
    private String photoUrl = "";
    private Role role;





}