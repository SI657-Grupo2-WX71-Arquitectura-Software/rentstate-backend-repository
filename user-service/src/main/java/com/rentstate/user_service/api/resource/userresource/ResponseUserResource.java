package com.rentstate.user_service.api.resource.userresource;

import com.rentstate.user_service.model.aggregates.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ResponseUserResource {


    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private String gender;
    private String description;
    private Boolean isPremium;
    private String password;
    private String photoUrl;
    private Integer rankPoints;


    public ResponseUserResource(User user) {

        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.password = user.getPassword();
        this.description = user.getDescription();
        this.isPremium = user.getIsPremium();
        this.photoUrl = user.getPhotoUrl();
        this.rankPoints = 5;
    }
}