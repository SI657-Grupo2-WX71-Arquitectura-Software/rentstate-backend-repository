package com.rentstate.auth_service.resource.api.resource.userresource;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class CreateUserResource {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
