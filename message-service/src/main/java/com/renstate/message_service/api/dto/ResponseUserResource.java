package com.renstate.message_service.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserResource {


    private Long id;
    private String name;
    private String username;
    private String lastName;
    private String gender;
    private String description;
    private String password;
    private String photoUrl;
    private Date birthDate;
    private Role role;



    public ResponseUserResource(User user) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.birthDate = user.getBirthDate();
        this.role = user.getRole();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.password = user.getPassword();
        this.description = user.getDescription();
        this.photoUrl = user.getPhotoUrl();

    }

}