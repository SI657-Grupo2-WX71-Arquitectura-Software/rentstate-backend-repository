package com.rentstate.auth_service.resource.api.resource.userresource;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String lastName;
    private String username;
    private String password;
}
