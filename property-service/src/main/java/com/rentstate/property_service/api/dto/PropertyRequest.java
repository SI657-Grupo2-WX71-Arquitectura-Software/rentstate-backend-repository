package com.rentstate.property_service.api.dto;

import lombok.Data;

@Data
public class PropertyRequest {
    private String title;
    private String description;
    private String characteristics;
    private String location;
    private String category;
}
