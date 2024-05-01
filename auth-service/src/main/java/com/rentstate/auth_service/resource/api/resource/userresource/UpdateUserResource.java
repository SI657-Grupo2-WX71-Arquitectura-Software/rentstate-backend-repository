package com.rentstate.auth_service.resource.api.resource.userresource;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UpdateUserResource {
    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private String gender;
    private String description;
    private Boolean isPremium;
    private String photoUrl;

}