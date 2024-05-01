package com.rentstate.auth_service.resource.api.resource.userresource;

import com.rentstate.auth_service.resource.api.resource.User;
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
    private String username;

    public ResponseUserResource(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
    }
}