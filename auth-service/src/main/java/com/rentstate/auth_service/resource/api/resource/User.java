package com.rentstate.auth_service.resource.api.resource;

import com.rentstate.auth_service.resource.api.resource.userresource.UpdateUserResource;
import jakarta.persistence.*;
import lombok.*;


@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String password;

}
